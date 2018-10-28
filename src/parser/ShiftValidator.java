package parser;

import com.beust.jcommander.ParameterException;
import com.beust.jcommander.IParameterValidator;

public class ShiftValidator implements IParameterValidator {

  @Override
  public void validate(String name, String value) throws ParameterException {
    int shift = Integer.parseInt(value);

    if (shift < 1 || shift > 26) {
      throw new ParameterException("Parameter " + name + " should be between 1 and 26");
    }
  }
}
