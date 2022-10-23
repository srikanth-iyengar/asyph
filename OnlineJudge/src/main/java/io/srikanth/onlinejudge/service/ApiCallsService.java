package io.srikanth.onlinejudge.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import io.srikanth.onlinejudge.models.JudgeResponse;
import reactor.core.publisher.Mono;

@Service
public class ApiCallsService {

	@Autowired
	private WebClient.Builder webClientBuilder;

	static final String baseDir = "testcases/";


	
	@Value("${environment.prod}")
	private Boolean production;
	/*
	 *This is the utility functions that unzip the testcases and saves the file in 
	 *the correct directory
	 */
	/**START OF UNZIP CODE**/
	public String saveTestCases(MultipartFile file, String problemId, String contestId) throws Exception {
		File destDir = new File((production ? "~/" : "") + baseDir+"contest_"+contestId+"/" + problemId +"/");
		ZipInputStream zips = new ZipInputStream(file.getInputStream());
		byte buffer[] = new byte[1024];
		ZipEntry zipEntry = zips.getNextEntry();
		while(zipEntry!=null) {
			File newFile = newFile(destDir, zipEntry);
			if(zipEntry.isDirectory()) {
				if(!newFile.isDirectory() && newFile.mkdirs()) {
					throw new IOException("Failed to save testcase"+ newFile);
				}
			}
			else {
				File parent = newFile.getParentFile();
				if(!parent.isDirectory() && !parent.mkdirs()) {
					throw new IOException("Failed to create parent directory"+ parent);
				}
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while((len = zips.read(buffer)) > 0) {
					fos.write(buffer);
				}
				fos.close();
			}
			zipEntry = zips.getNextEntry();
		}
		zips.closeEntry();
		zips.close();
		return "Tests uploaded successfully";
	}

	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
		File destFile = new File(destinationDir, zipEntry.getName());
		String destDirPath = destinationDir.getCanonicalPath();
		String destFilePath = destFile.getCanonicalPath();
		if (!destFilePath.startsWith(destDirPath + File.separator)) {
			throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
		}
		return destFile;
	}

	public void deleteTestCases(String problemId, String contestId) throws InterruptedException, IOException{
		String command = "rm -rf testcases/contest_" + contestId + "/" + problemId;
		ProcessBuilder builder = new ProcessBuilder("bash", "-c", command);
		builder.start();
	}
	/**END OF UNZIP CODE**/

	/**This is function that sends the status of the submission to the user microservice**/
	@Async("sendSubmissionStatus")
	public void sendDataToUserService(JudgeResponse response) {
		try {
			webClientBuilder.build()
				.put()
				.uri("http://USER-SERVICE/update-submission")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(response), JudgeResponse.class)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		}
		catch(Exception e) {
			System.out.println("Exception occured");
			return;
		}
		System.out.println("DataUpdated");
	}
}
