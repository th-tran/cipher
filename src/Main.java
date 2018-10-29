import java.io.BufferedReader;
import java.io.InputStreamReader;

import cryptography.*;
import parser.*;
import util.*;

import com.beust.jcommander.JCommander;

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
    switch (settings.algo) {
      case RSA:
        // TODO
        break;
      case SHIFT:
        if (settings.shift == 0) {
          throw new IllegalStateException("Cannot start encryption: missing shift value (provide with -s flag)");
        }
        System.out.println("Enter the plain text:");
        msg = IOUtilities.getUserInput();
        System.out.println("Original message: " + msg);
        Shift cipher = new Shift(settings.shift);
        msg = cipher.encrypt(msg);
        System.out.println("Encrypted message: " + msg);
        msg = cipher.decrypt(msg);
        System.out.println("Decrypted message: " + msg);
        break;
      default:
        // Should be handled by parser, but throw exception here anyways
        throw new IllegalArgumentException("Invalid algorithm: " + settings.algo);
    }
  }
}
