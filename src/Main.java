import cryptography.*;
import parser.*;
import util.*;

import com.beust.jcommander.JCommander;
import java.util.Random;

public class Main {
  
  public static void main(String[] argv) throws Exception {
    Settings settings = new Settings();
    // Most invalid/edge cases will be caught during parsing
    new JCommander(settings, argv);
    new Main().run(settings);
  }
    
  public void run(Settings settings) throws Exception { 
    //System.out.println(settings.algo);
    //System.out.println(settings.shift);
    String msg;
    String launchInfo;
    switch (settings.algo) {
      case RSA:
        launchInfo = String.format("--- Starting %s ('RSA' algorithm) ---", (settings.decrypt) ? "decryption" : "encryption");
        System.out.println(launchInfo);
        RSA rsaCipher = new RSA();
        // XXX Testing (only works with numbers right now)
        double test = 12;
        System.out.println(String.format("Original message: %f", test));
        test = rsaCipher.encrypt(test, rsaCipher.e, rsaCipher.n);
        System.out.println(String.format("Encrypted message: %f", test));
        test = rsaCipher.decrypt(test, rsaCipher.d, rsaCipher.n);
        System.out.println(String.format("Decrypted message: %f", test));
        break;
      case SHIFT:
        if (settings.shift == 0) {
          Random random = new Random();
          int min = 1; // inclusive
          int max = 26; // exclusive
          settings.shift = random.nextInt(max - min) + min;
        }
        launchInfo = String.format("--- Starting %s ('Shift' algorithm, shift=%1d) ---", (settings.decrypt) ? "decryption" : "encryption", settings.shift);
        System.out.println(launchInfo);
        Shift shiftCipher = new Shift(settings.shift);
        if (settings.inputFile != null && !settings.inputFile.isEmpty()) {
          msg = IOUtilities.readFromFile(settings.inputFile, true);
        } else {
          System.out.println("Enter the plain text:");
          msg = IOUtilities.getUserInput();
        }
        System.out.println("Original message: " + msg);
        if (settings.decrypt) {
          msg = shiftCipher.decrypt(msg);
          System.out.println("Decrypted message: " + msg);
        } else {
          msg = shiftCipher.encrypt(msg);
          System.out.println("Encrypted message: " + msg);
        }
        if (settings.outputFile != null && !settings.outputFile.isEmpty()){
          IOUtilities.writeToFile(settings.outputFile, msg, true);
        }
        break;
      default:
        // Should be handled by parser, but throw exception here anyways
        throw new IllegalArgumentException("Invalid algorithm: " + settings.algo);
    }
  }
}
