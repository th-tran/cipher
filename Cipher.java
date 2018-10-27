import algorithm.*;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Cipher {
  public static void main(String[] args) throws Exception {
    Caesar cipher = new Caesar();
    cipher.test();
  }
}
