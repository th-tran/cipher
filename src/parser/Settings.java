package parser;

import com.beust.jcommander.Parameter;

public class Settings {
  
  @Parameter(description="The algorithm to use to encrypt/decrypt your message", required=true, converter=AlgoConverter.class)
  public AlgoEnum algo;
  @Parameter(names={"--shift", "-s"}, description="The shift to apply to each character in your message", validateWith=ShiftValidator.class)
  public int shift;
  
}
