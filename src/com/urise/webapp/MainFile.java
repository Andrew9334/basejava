package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        printAllFiles(new File("C:\\Users\\MSI\\Desktop\\basejava"));
    }

    protected static void printAllFiles(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                printAllFiles(file);
            } else {
                System.out.println(file.getAbsolutePath());
            }
        }
    }
}
