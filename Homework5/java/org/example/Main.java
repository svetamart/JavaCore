package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Main {
    public static void main(String[] args) {

        String dir = "src/main/test";
        try {
            Path backupPath = createDirectoryBackup(dir);
            System.out.println("The directory backup has been successfully created: " + backupPath.toAbsolutePath().normalize());
        } catch (IOException e) {
            System.out.println("An error occurred while creating the directory backup. " + e.getMessage());
        }

        int[] board = {1, 0, 2, 3, 1, 0, 2, 0, 1};

        try {
            boardToByte(board);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Path createDirectoryBackup (String sourceDirectory) throws IOException {
        Path source = Paths.get(sourceDirectory);
        Path backup = Paths.get("./backup");

        if (!Files.exists(source)) {
            throw new IOException("Source directory not found");
        }

        if (Files.exists(backup)) {
            throw new IOException("Backup directory already exists");
        }

        Files.createDirectory(backup);

        File folder = new File(sourceDirectory);

        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles)
                Files.copy(file.toPath(), backup.resolve(file.getName()), REPLACE_EXISTING);
        }

        return backup;
    }

    public static void boardToByte (int[] board) throws IOException {

        if (board.length != 9) {
            throw new IllegalArgumentException("The board array must contain 9 values.");
        }

        byte[] data = new byte[3];
        int bitIndex = 0;
        int byteIndex = 0;

        for (int value : board) {

            data[byteIndex] |= (byte) (value << bitIndex);
            bitIndex += 2;

            if (bitIndex >= 8) {
                bitIndex = 0;
                byteIndex++;
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream file = new BufferedOutputStream(new FileOutputStream("board.txt"));

        for (int i = 0; i < board.length; i++) {
            byteArrayOutputStream.write(board[i]);
        }

        file.write(data);

        System.out.println("Board data written to file.");

        file.close();
        byteArrayOutputStream.close();

        Path filePath = Paths.get("board.txt");
        long fileSize = Files.size(filePath);

        System.out.println("File size: " + fileSize + " bytes.");
    }
}