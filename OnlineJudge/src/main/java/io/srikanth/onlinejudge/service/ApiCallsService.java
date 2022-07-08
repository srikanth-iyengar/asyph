package io.srikanth.onlinejudge.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApiCallsService {
	static final String baseDir = "testcases/";
	public String saveTestCases(MultipartFile file, String problemId, String contestId) throws Exception {
		File destDir = new File(baseDir+"contest_"+contestId+"/" + problemId +"/");
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
}
