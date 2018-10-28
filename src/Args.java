import com.beust.jcommander.Parameter;

public class Args {
 
  @Parameter(description="Encryption algorithm")
  String alg;
  @Parameter(names={"--shift", "-s"})
  int shift;
 
}
