package com.beanbroker.test.file;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;

import static com.beanbroker.test.file.BeanbrokerFileWorker.getWorkingDirectory;

public class FileObserverUpgrade {

    public static void main(String[] args) {

        String searchFileFolder = "search_file";
        String targetDirectory = getWorkingDirectory() + "/" + searchFileFolder;
        observeFileStatus(targetDirectory);

    }

    private static void observeFileStatus(String targetDirectory) {


        Path faxFolder = Paths.get(targetDirectory);

        try {

            //워치 서비스에 이벤트들 등록
            WatchService fileWatchService = FileSystems.getDefault().newWatchService();
            faxFolder.register(fileWatchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.OVERFLOW

                    );

            boolean valid = true;
            do {
                WatchKey watchKey = fileWatchService.take();

                for (WatchEvent event : watchKey.pollEvents()) {

                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                        String fileName = event.context().toString();
                        System.out.println("start to notify file Created :" + fileName + " , time : " + LocalDateTime.now());
                    }else if(StandardWatchEventKinds.ENTRY_MODIFY.equals(event.kind())){
                        String fileName = event.context().toString();
                        System.out.println("start to notify file modified :" + fileName + " , time : " + LocalDateTime.now());
                    }else if(StandardWatchEventKinds.ENTRY_DELETE.equals(event.kind())){
                        String fileName = event.context().toString();
                        System.out.println("start to notify file deleted :" + fileName + " , time : " + LocalDateTime.now());
                    }else if(StandardWatchEventKinds.OVERFLOW.equals(event.kind())) {
                        String fileName = event.context().toString();
                        System.out.println("start to notify OVERFLOW  time : " + LocalDateTime.now());
                    }else{
                        System.out.println("UNKNOWN EVENT ......");
                    }
                }
                valid = watchKey.reset();

            } while (valid);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
