package main.java.dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Writer {

	/*
	 * Creates a report file with the dat extension inside home/data/out folder
	 */
	public void createAndWriteReport(String fileName, String question1, String question2, String question3,
			String question4) {
		Path folderPath = Paths.get(System.getProperty("user.home") + "/data/out");
		List<String> lines = Arrays.asList(question1, question2, question3, question4);
		String fullPathFile = folderPath.toString() + "\\" + fileName + ".done.dat";
		Path file = Paths.get(fullPathFile);
		try {
			Files.write(file, lines, Charset.forName("iso-8859-1"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
