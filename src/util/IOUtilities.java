package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public final class IOUtilities {

  public static String getUserInput() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    br.close();
    return input;
  }

  public static String assertTxt(String fileName) {
    if (!fileName.endsWith(".txt")) {
      if (!fileName.contains(".")) {
        fileName += ".txt";
      } else {
        throw new IllegalArgumentException("The file to read must be a .txt file.");
      }
    }
    return fileName;
  }

  public static String readTextFromFile(String fileName) throws IOException {
    fileName = assertTxt(fileName);
    System.out.println("Reading from text file: " + fileName);
    File file = new File(System.getProperty("user.dir") + "/" + fileName);
    BufferedReader br = new BufferedReader(new FileReader(file));
    String fileContent = "";
    try {
      String line;
      while((line = br.readLine()) != null) {
        fileContent += line;
      }
    } finally {
      br.close();
    }
    return fileContent;
  }

  public static byte[] readByteArrayFromFile(String fileName) throws IOException {
    fileName = assertTxt(fileName);
    System.out.println("Reading from text file: " + fileName);
    File file = new File(System.getProperty("user.dir") + "/" + fileName);
    byte[] fileContent = Files.readAllBytes(file.toPath());
    return fileContent;
  }

  public static boolean writeToFile(String fileName, String fileContent) throws IOException {
    fileName = assertTxt(fileName);
    System.out.println("Writing to text file: " + fileName);
    File file = new File(System.getProperty("user.dir") + "/" + fileName);
    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    try {
      bw.write(fileContent);
    } finally {
      bw.close();
    }
    return true;
  }

  public static boolean writeToFile(String fileName, byte[] bytes) throws IOException {
    fileName = assertTxt(fileName);
    System.out.println("Writing to text file: " + fileName);
    File file = new File(System.getProperty("user.dir") + "/" + fileName);
    Files.write(file.toPath(), bytes);
    return true;
  }
}
