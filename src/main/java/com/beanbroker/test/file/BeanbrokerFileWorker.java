package com.beanbroker.test.file;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeanbrokerFileWorker {

    public static void main(String[] args) {

        String searchFileFolder = "search_file";
        String targetDirectory = getWorkingDirectory() + "/" + searchFileFolder;

        //1  자신의 워킹디렉토리를 알아보자

//        System.out.println("==================================================================================");
//        System.out.println(getWorkingDirectory());
//        String searchFileFolder = "search_file";
//        System.out.println(getWorkingDirectory()+"/" + searchFileFolder);
//        System.out.println("==================================================================================");


//        //2. 해당 하위 디렉토리 아래 모든 파일 가져오기
//        List<String> allFiles = getAllFiles(targetDirectory);
//        allFiles.forEach(System.out::println);
//
//
//        //3. 해당 하위 디렉토리 아래 모든 폴더 가져오기
//        List<String> allFolders = getAllFolders(targetDirectory);
//        allFolders.forEach(System.out::println);

         //4. 해당 하위 디렉토리 아래 목표로하는 확장자 파일들가져오기
//        String targetFileExtension = "txt";
//        List<String> allFilesUnderFolders = getAllFilesWithFileExtension(targetDirectory, targetFileExtension);
//        allFilesUnderFolders.forEach(System.out::println);


        //5. 해당 하위데릭토리에서 원하는 파일 찾기
        String targetFileName = "test1.txt";
        List<String> tatgetFile = findFileUnderTargetFolder(targetDirectory, targetFileName);
        tatgetFile.forEach(System.out::println);

    }

    public static String getWorkingDirectory() {

        return System.getProperty("user.dir");
    }

    public static List<String> getAllFiles(String targetPath) {

        try (Stream<Path> walk = Files.walk(Paths.get(targetPath))) {

            return walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getAllFolders(String targetPath) {

        try (Stream<Path> walk = Files.walk(Paths.get(targetPath))) {

            return walk.filter(Files::isDirectory)
                    .map(Path::toString).collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<String> getAllFilesWithFileExtension(String targetPath, String extension) {

        try (Stream<Path> walk = Files.walk(Paths.get(targetPath))) {

            return walk.map(Path::toString)
                    .filter(f -> f.endsWith(extension)).collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> findFileUnderTargetFolder(String targetPath, String fileName) {

        try (Stream<Path> walk = Files.walk(Paths.get(targetPath))) {


            return walk.map(Path::toString)
                    .filter(f -> f.contains(fileName))
                    .collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
