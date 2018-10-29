package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
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
  public static String readFromFile(String fileName, boolean verbose) throws IOException {
    fileName = assertTxt(fileName);
    if (verbose) {
      System.out.println("Reading from file: " + fileName);
    }
    BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/" + fileName));
    String fileContent = "";
    String line;
    while((line = br.readLine()) != null) {
      fileContent += line;
    }
    br.close();
    return fileContent;
  }
  public static boolean writeToFile(String fileName, String fileContent, boolean verbose) throws IOException {
    fileName = assertTxt(fileName);
    if (verbose) {
      System.out.println("Writing to file: " + fileName);
    }
    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(System.getProperty("user.dir") + "/" + fileName)));
    bw.write(fileContent);
    bw.close();
    return true;
  }
}
