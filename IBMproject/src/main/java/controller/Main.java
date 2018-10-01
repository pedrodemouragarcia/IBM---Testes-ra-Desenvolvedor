package main.java.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import main.java.dao.Reader;

public class Main {

	public static void main(String[] args) {

		WatchService watchService;
		try {
			watchService = FileSystems.getDefault().newWatchService();
			Path path = Paths.get(System.getProperty("user.home") + "/data/in");
			Reader reader = new Reader();
			reader.checkFiles(path);
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
			WatchKey key;
			while ((key = watchService.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					String fileName = event.context().toString();
					String fullFilePath = path + "/" + fileName;
					reader.checkAddFile(fullFilePath, fileName);
				}
				key.reset();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}
}
