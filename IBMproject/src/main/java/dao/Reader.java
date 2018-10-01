package main.java.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.controller.Manipulator;

public class Reader {
	/*
	 * List all files inside the folder
	 */
	private List<String> collectDatFile(Path root) throws IOException {
		try (Stream<Path> files = Files.walk(root)) {
			return files.filter(p -> Files.isRegularFile(p)).filter(p -> p.getFileName().toString().endsWith(".dat"))
					.map(p -> root.relativize(p).toString()).collect(Collectors.toList());
		}
	}

	/*
	 * Read and check all files inside the folder
	 */
	public void checkFiles(Path root) throws IOException {
		List<String> filesToRead = collectDatFile(root);
		Manipulator m = new Manipulator();
		for (int x = 0; x < filesToRead.size(); x++) {
			try {
				FileInputStream input = new FileInputStream(new File(root + "/" + filesToRead.get(x)));
				CharsetDecoder decoder = Charset.forName("iso-8859-1").newDecoder();
				InputStreamReader reader = new InputStreamReader(input, decoder);
				BufferedReader bufferedReader = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				String line = bufferedReader.readLine();
				m.checkLine(line);
				while (line != null) {
					sb.append(line);
					line = bufferedReader.readLine();
					if (line != null) {
						m.checkLine(line);
					}
				}
				bufferedReader.close();
				String fileName = filesToRead.get(x).toString();
				m.beginReport(fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Read and check a file added or uploaded in the folder
	 */
	public void checkAddFile(String path, String file) throws IOException {
		Manipulator m = new Manipulator();
		if (path.endsWith(".dat")) {
			try {
				FileInputStream input = new FileInputStream(new File(path));
				CharsetDecoder decoder = Charset.forName("iso-8859-1").newDecoder();
				InputStreamReader reader = new InputStreamReader(input, decoder);
				BufferedReader bufferedReader = new BufferedReader(reader);
				StringBuilder sb = new StringBuilder();
				String line = bufferedReader.readLine();
				m.checkLine(line);
				while (line != null) {
					sb.append(line);
					line = bufferedReader.readLine();
					if (line != null) {
						m.checkLine(line);
					}
				}
				bufferedReader.close();
				m.beginReport(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
