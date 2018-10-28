package parser;

import com.beust.jcommander.ParameterException;
import com.beust.jcommander.IStringConverter;

public class AlgoConverter implements IStringConverter<AlgoEnum> {

  @Override
  public AlgoEnum convert(String value) {
    AlgoEnum convertedValue = AlgoEnum.fromString(value);

    if (convertedValue == null) {
      throw new ParameterException("Value " + value + " cannot be converted to AlgoEnum. " +
          "Available values are: rsa, shift.");
    }
    return convertedValue;
  }
}
