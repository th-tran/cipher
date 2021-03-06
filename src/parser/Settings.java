package parser;

import com.beust.jcommander.Parameter;

public class Settings {

  @Parameter(description="The algorithm to use to encrypt/decrypt your message.", required=true, converter=AlgoConverter.class)
  public AlgoEnum algo = null;
  @Parameter(names={"--shift", "-s"}, description="The shift to apply to each character in your message. By default, a random shift is selected (between 1 to 25)", validateWith=ShiftValidator.class)
  public int shift = 0;
  @Parameter(names={"--decrypt", "-d"}, description="Decrypt mode.")
  public boolean decrypt = false;
  @Parameter(names={"--key", "-k"}, description="The key file containing values to initialize an algorithm.")
  public String keyFile = "";
  @Parameter(names={"--file", "-f"}, description="The file containing the message to encrypt/decrypt.")
  public String inputFile = "";
  @Parameter(names={"--output", "-o"}, description="Write results to a file.")
  public String outputFile = "";
}
