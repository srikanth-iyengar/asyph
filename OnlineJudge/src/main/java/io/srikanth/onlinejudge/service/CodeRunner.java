package io.srikanth.onlinejudge.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.srikanth.onlinejudge.models.Language;
import io.srikanth.onlinejudge.models.RunRequest;
import io.srikanth.onlinejudge.models.RunnerResponse;
import io.srikanth.onlinejudge.repository.RequestRepository;

@Service
public class CodeRunner {

	@Autowired
	public RequestRepository requestRepository;

	final Logger logger = LogManager.getLogger(CodeRunner.class);

	public RunnerResponse initiateRequest(RunRequest request) throws IOException, InterruptedException {
		RunnerResponse response = new RunnerResponse();
		response.setToken(UUID.randomUUID().toString().replace("-", ""));
		response.setVerdict("QUEUED");
		requestRepository.save(response);
		return response;
	}

	@Async("processRunner")
	public void run(RunRequest request, RunnerResponse response) throws IOException, InterruptedException {
		StringBuilder compile = new StringBuilder();
		StringBuilder run = new StringBuilder();
		File codeFile;
		String className;
		run.append("timeout " + request.getTimeLimit() + " ");
		switch (request.getCompiler()) {
		case JAVA8:
			request.setCode(matchPattern(request.getCode(), response.getToken()));
			className = "Main" + response.getToken();
			codeFile = new File("code/" + className + ".java");
			compile.append("javac -d java_classes code/" + className + ".java");
			run.append("java -cp java_classes " + className);
			break;
		case JAVA11:
			request.setCode(matchPattern(request.getCode(), response.getToken()));
			className = "Main" + response.getToken();
			codeFile = new File("code/" + className + ".java");
			compile.append("javac -d java_classes code/" + className + ".java");
			run.append("java -cp java_classes " + className);
			break;
		case JAVA17:
			request.setCode(matchPattern(request.getCode(), response.getToken()));
			className = "Main" + response.getToken();
			codeFile = new File("code/" + className + ".java");
			compile.append("javac -d java_classes code/" + className + ".java");
			run.append("java -cp java_classes " + className);
			break;
		case CPP11:
			request.setCode(matchPattern(request.getCode(), response.getToken()));
			codeFile = new File("code/Main.cpp");
			compile.append("g++ code/Main.cpp -o code/Main" + response.getToken());
			run.append("code/Main" + response.getToken());
			break;
		case CPP14:
			codeFile = new File("code/Main.cpp");
			compile.append("g++ code/Main.cpp -o code/Main" + response.getToken());
			run.append("code/Main" + response.getToken());
			break;
		case CPP17:
			codeFile = new File("code/Main.cpp");
			compile.append("g++ code/Main.cpp -o code/Main" + response.getToken());
			run.append("code/Main" + response.getToken());
			break;
		case CPP20:
			codeFile = new File("code/Main.cpp");
			compile.append("g++ code/Main.cpp -o code/Main" + response.getToken());
			run.append("code/Main" + response.getToken());
			break;
		case PYTHON2:
			codeFile = new File("code/Main.py");
			run.append("python2 code/Main.py");
			break;
		case PYTHON3:
			codeFile = new File("code/Main.py");
			run.append("python3 code/Main.py");
			break;
		case PYPY2:
			codeFile = new File("code/Main.py");
			break;
		case PYPY3:
			codeFile = new File("code/Main.py");
			break;
		default:
			System.out.println("Default");
			return;
		}
		codeFile.createNewFile();
		PrintWriter pw = new PrintWriter(codeFile);
		pw.write(request.getCode());
		pw.close();
		if (compile.length() != 0) {
			ProcessBuilder builder = new ProcessBuilder("bash", "-c", compile.toString());
			Process compiling = builder.start();
			compiling.waitFor();
			if (compiling.exitValue() != 0) {
				deleteFiles(codeFile, new File("participant/" + response.getToken() + ".out"), request.getCompiler(),
						response.getToken());
				response.setVerdict("COMPILATION_ERROR");
				requestRepository.save(response);
				return;
			}
		}
		response.setVerdict("RUNNING");
		requestRepository.save(response);
		final String tc_dir = "testcases/contest_" + request.getContest_id() + "/" + request.getProblem_index() + "/";
		boolean tests_passed = true;
		for (int i = 0; i < request.getTestCases(); i++) {
			String input = tc_dir + "test" + i + ".in";
			String output = tc_dir + "test" + i + ".out";
			String participant_output = "participant/" + response.getToken() + ".out";
			run.append(" < " + input + " > " + participant_output);
			Process program = new ProcessBuilder("bash", "-c", run.toString()).start();
			program.waitFor();
			int exitCode = program.exitValue();
			if (exitCode == 1) {
				tests_passed = false;
				response.setVerdict("RUNTIME_ERROR");
				break;
			} else if (exitCode == 124) {
				tests_passed = false;
				response.setVerdict("TIME_LIMIT_EXCEED");
				break;
			} else {
				if (!contentEquals(new File(output), new File(participant_output))) {
					tests_passed = false;
					response.setVerdict("WRONG_ANSWER");
					break;
				}
			}
		}
		if (tests_passed)
			response.setVerdict("ACCEPTED");
		deleteFiles(codeFile, new File("participant/" + response.getToken() + ".out"), request.getCompiler(),
				response.getToken());
		requestRepository.save(response);
		logger.info("Verdict: " + response.getVerdict());
	}

	public void deleteFiles(File code, File participantOutput, Language language, String token) {
		switch (language) {
		case JAVA8:
			deleteFile(new File("java_classes/Main" + token + ".class"));
			break;
		case JAVA11:
			deleteFile(new File("java_classes/Main" + token + ".class"));
			break;
		case JAVA17:
			deleteFile(new File("java_classes/Main" + token + ".class"));
			break;
		case CPP11:
			deleteFile(new File("code/Main" + token));
			break;
		case CPP14:
			deleteFile(new File("code/Main" + token));
			break;
		case CPP17:
			deleteFile(new File("code/Main" + token));
			break;
		case CPP20:
			deleteFile(new File("code/Main" + token));
			break;
		default:
			break;
		}
		deleteFile(code);
		deleteFile(participantOutput);
	}

	public void deleteFile(File file) {
		file.delete();
	}

	public boolean contentEquals(File f1, File f2) throws IOException {
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(f1));
			BufferedReader br2 = new BufferedReader(new FileReader(f2));
			while (true) {
				String s1 = br1.readLine();
				String s2 = br2.readLine();
				if (s1 == null && s2 == null) {
					break;
				}
				s1 = s1.trim();
				s2 = s2.trim();
				if (!s1.equals(s2)) {
					br1.close();
					br2.close();
					return false;
				}
			}
			br1.close();
			br2.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	public String matchPattern(String code, String token) {
		Pattern JAVA_CLASS_NAME = Pattern.compile("(public class [a-z|A-Z|0-9]*)");
		Matcher m = JAVA_CLASS_NAME.matcher(code);
		StringBuilder sb = new StringBuilder();
		String repString = "public class Main" + token;
		if (m.find())
			m.appendReplacement(sb, repString);
		m.appendTail(sb);
		return sb.toString();
	}
}