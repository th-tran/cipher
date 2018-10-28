import algorithm.*;
import com.beust.jcommander.JCommander;

public class Cipher {
    
  public static void main(String[] argv) throws Exception {
    Args args = new Args();
    JCommander.newBuilder()
            .addObject(args)
            .build()
            .parse(argv);
    System.out.println(args.alg);
    System.out.println(args.shift);
    Caesar cipher = new Caesar();
    cipher.test();
  }
}
