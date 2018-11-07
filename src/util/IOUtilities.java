package util;

import cryptography.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;

public final class IOUtilities {

  public static String getUserInput() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    br.close();
    return input;
  }

  public static String assertFileExtension(String fileName, String fileExtension) {
    if (!(fileExtension.startsWith("."))) {
      fileExtension = "." + fileExtension;
    }
    if (!fileName.endsWith(fileExtension)) {
      if (!fileName.contains(".")) {
        fileName += fileExtension;
      } else {
        throw new IllegalArgumentException(String.format("The file to read must be a %s file.", fileExtension));
      }
    }
    return fileName;
  }

  public static String readTextFromFile(String fileName) throws IOException {
    fileName = assertFileExtension(fileName, ".txt");
    System.out.println("Reading from file: " + fileName);
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
    fileName = assertFileExtension(fileName, ".txt");
    System.out.println("Reading bytes from file: " + fileName);
    File file = new File(System.getProperty("user.dir") + "/" + fileName);
    byte[] fileContent = Files.readAllBytes(file.toPath());
    return fileContent;
  }

  public static boolean writeToFile(String fileName, String fileContent) throws IOException {
    fileName = assertFileExtension(fileName, ".txt");
    System.out.println("Writing text to file: " + fileName);
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
    fileName = assertFileExtension(fileName, ".txt");
    System.out.println("Writing bytes to file: " + fileName);
    File file = new File(System.getProperty("user.dir") + "/" + fileName);
    Files.write(file.toPath(), bytes);
    return true;
  }

  public static RSA readRSAKey(String keyName) throws IOException {
    keyName = assertFileExtension(keyName, ".key");
    System.out.println("Reading from key: " + keyName);
    File keyFile = new File(System.getProperty("user.dir") + "/" + keyName);
    BufferedReader br = new BufferedReader(new FileReader(keyFile));
    RSA rsa;
    try {
      String line;
      BigInteger e = new BigInteger("0");
      BigInteger d = new BigInteger("0");
      BigInteger N = new BigInteger("0");
      while((line = br.readLine()) != null) {
        String key = line.replaceAll("\\P{L}", "");
        String value = line.replaceAll("[^\\d.]", "");
        if (key.contains("e")) {
          e = new BigInteger(value);
        } else if (key.contains("d")) {
          d = new BigInteger(value);
        } else if (key.contains("N")) {
          N = new BigInteger(value);
        } else {
          throw new IllegalArgumentException("Invalid key format: " + key);
        }
      }
      rsa = new RSA(e, d, N);
    } finally {
      br.close();
    }
    return rsa;
  }

  public static boolean writeKey(String keyName, RSA rsa) throws IOException {
    keyName = assertFileExtension(keyName, ".key");
    System.out.println("Writing to key file: " + keyName);
    File keyFile = new File(System.getProperty("user.dir") + "/" + keyName);
    BufferedWriter bw = new BufferedWriter(new FileWriter(keyFile));
    try {
      bw.write("e: " + rsa.getE() + System.getProperty("line.separator"));
      bw.write("d: " + rsa.getD() + System.getProperty("line.separator"));
      bw.write("N: " + rsa.getN());
    } finally {
      bw.close();
    }
    return true;
  }
}
