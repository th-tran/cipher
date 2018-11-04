import cryptography.*;
import parser.*;
import util.*;

import com.beust.jcommander.JCommander;

import java.util.Random;

public class Main {
  
  private String endl = System.getProperty("line.separator");
  private String separator = endl + "----------------------------" + endl;

  public static void main(String[] argv) throws Exception {
    Settings settings = new Settings();
    // Most invalid/edge cases will be caught during parsing
    new JCommander(settings, argv);
    new Main().run(settings);
  }
    
  public void run(Settings settings) throws Exception { 
    String message = "";
    String launchInfo = "";
    switch (settings.algo) {
      case RSA:
        launchInfo = String.format("--- Starting %s ('RSA' algorithm) ---", (settings.decrypt) ? "decryption" : "encryption");
        System.out.println(launchInfo);
        RSA rsaCipher = new RSA();

        byte[] messageAsBytes;
        if (settings.inputFile != null && !settings.inputFile.isEmpty() && settings.decrypt) {
          messageAsBytes = IOUtilities.readByteArrayFromFile(settings.inputFile);
        } else if (settings.inputFile != null && !settings.inputFile.isEmpty() && !(settings.decrypt)) {
          message = IOUtilities.readTextFromFile(settings.inputFile);
          messageAsBytes = message.getBytes();
        } else {
          System.out.println("Enter the plain text:");
          message = IOUtilities.getUserInput();
          messageAsBytes = message.getBytes();
        }

        System.out.println(separator);

        if (settings.decrypt) {
          System.out.println("Decrypting string: " + messageAsBytes.toString());
          messageAsBytes = rsaCipher.decrypt(messageAsBytes);
          System.out.println(String.format("Decrypted message: %s", new String(messageAsBytes)));
        } else {
          System.out.println(String.format("Encrypting string: %s", message));
          messageAsBytes = rsaCipher.encrypt(messageAsBytes);
          System.out.println("Encrypted message: " + messageAsBytes.toString());
        }

        if (settings.outputFile != null && !settings.outputFile.isEmpty()){
          IOUtilities.writeToFile(settings.outputFile, messageAsBytes);
        }

        break;
      case SHIFT:
        launchInfo = String.format("--- Starting %s ('Shift' algorithm, shift=%1d) ---", (settings.decrypt) ? "decryption" : "encryption", settings.shift);
        System.out.println(launchInfo);
        if (settings.shift == 0) {
          Random random = new Random();
          int min = 1; // inclusive
          int max = 26; // exclusive
          settings.shift = random.nextInt(max - min) + min;
        }
        Shift shiftCipher = new Shift(settings.shift);

        if (settings.inputFile != null && !settings.inputFile.isEmpty()) {
          message = IOUtilities.readTextFromFile(settings.inputFile);
        } else {
          System.out.println("Enter the plain text:");
          message = IOUtilities.getUserInput();
        }

        System.out.println(separator);

        if (settings.decrypt) {
          System.out.println("Decrypting string: " + message + endl);
          message = shiftCipher.decrypt(message);
          System.out.println("Decrypted message: " + message);
        } else {
          System.out.println("Encrypting string: " + message + endl);
          message = shiftCipher.encrypt(message);
          System.out.println("Encrypted message: " + message);
        }

        if (settings.outputFile != null && !settings.outputFile.isEmpty()){
          IOUtilities.writeToFile(settings.outputFile, message);
        }

        break;
      default:
        // Should be handled by parser, but throw exception here anyways
        throw new IllegalArgumentException("Invalid algorithm: " + settings.algo);
    }
  }
}
