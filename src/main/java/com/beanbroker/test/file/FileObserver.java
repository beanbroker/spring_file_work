package com.beanbroker.test.file;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.beanbroker.test.file.BeanbrokerFileWorker.getWorkingDirectory;

public class FileObserver {

    public static void main(String[] args) {

        String searchFileFolder = "search_file";
        String targetDirectory = getWorkingDirectory() + "/" + searchFileFolder;
        observeFileCreated(targetDirectory);

    }

    private static void observeFileCreated(String targetDirectory) {


        Path faxFolder = Paths.get(targetDirectory);

        try {

            WatchService fileWatchService = FileSystems.getDefault().newWatchService();
            faxFolder.register(fileWatchService, StandardWatchEventKinds.ENTRY_CREATE);

            boolean valid = true;
            do {
                WatchKey watchKey = fileWatchService.take();

                for (WatchEvent event : watchKey.pollEvents()) {
                    WatchEvent.Kind kind = event.kind();
                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                        String fileName = event.context().toString();
                        System.out.println("start to notify file Created :" + fileName + " , time : " + LocalDateTime.now());
                    }
                }
                valid = watchKey.reset();

            } while (valid);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
