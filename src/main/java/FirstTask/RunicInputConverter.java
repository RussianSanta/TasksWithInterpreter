package FirstTask;

import helpers.InputConverter;

public class RunicInputConverter implements InputConverter {
    @Override
    public String convert(char c) {
        boolean isLow = Character.isLowerCase(c);
        if (isLow) {
            return "a";
        } else {
            return "A";
        }
    }
}
