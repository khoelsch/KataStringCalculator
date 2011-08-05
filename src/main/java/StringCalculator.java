import java.util.ArrayList;
import java.util.List;

/**
 * Calculate sums based on String input.
 *
 * @see <a href="http://osherove.com/tdd-kata-1">A video of this kata. Scroll down for a step-by-step How-to.</a>
 */
public class StringCalculator {

  private final static String[] NUMBER_DELIMITERS = {",", "\n"};

  private final static String CUSTOM_MARKER = "//";
  private final static String CUSTOM_DELIMITER = "\n";

  static private boolean containsDelimiter(final String number, String[] delimiters) {
    int delimIndex = 0;
    while (delimIndex < delimiters.length) {
      if (number.contains(delimiters[delimIndex])) {
        return true;
      }
      ++delimIndex;
    }
    return false;
  }

  static private int firstHit(final String number, String[] delimiters) {
    for (int i = 0; i < delimiters.length; ++i) {
      if (number.contains(delimiters[i])) {
        return number.indexOf(delimiters[i]);
      }
    }

    return -1;
  }

  static private List<String> splitNumber(final String number, String[] delimiters) {
    List<String> numbers = new ArrayList<String>();
    String rest = number;
    int hitPos = 0;

    while (containsDelimiter(rest, delimiters)) {
      hitPos = firstHit(rest, delimiters);
      numbers.add(rest.substring(0, hitPos));
      rest = rest.substring(hitPos+1);
    }

    //add last number, not delimiters left
    numbers.add(rest);

    return numbers;
  }

  static private String[] extractCustomDelimiter(final String number) {
    String[] newDelimiters = new String[NUMBER_DELIMITERS.length + 1];

    // copy existing delimiters
    System.arraycopy(NUMBER_DELIMITERS, 0, newDelimiters, 0, NUMBER_DELIMITERS.length);

    String customDelimiter = number.substring(number.indexOf(CUSTOM_MARKER)+CUSTOM_MARKER.length(),
            number.indexOf(CUSTOM_DELIMITER));

    newDelimiters[newDelimiters.length-1] = customDelimiter;

    return newDelimiters;
  }

  static int add(final String number) {
    if ("".equals(number) || "0".equals(number)) {
      return 0;
    }

    String[] runtimeDelimiters = NUMBER_DELIMITERS;
    String numberStr = number;

    try {
      if (!containsDelimiter(numberStr, runtimeDelimiters)) {
        return Integer.valueOf(numberStr).intValue();
      }

      // custom delimiter set?
      if (number.startsWith(CUSTOM_MARKER)) {
        runtimeDelimiters = extractCustomDelimiter(numberStr);
        numberStr = number.substring(number.indexOf(CUSTOM_DELIMITER)+1);
      }

      final List<String> numbers = splitNumber(numberStr, runtimeDelimiters);
      int sum = 0;
      for (String num : numbers) {
        sum += Integer.valueOf(num).intValue();
      }
      return sum;

    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("This kata is only designed for valid inputs!");
    } catch (Throwable ex) {
      throw new IllegalArgumentException("This kata is only designed for valid inputs!");
    }
  }
}
