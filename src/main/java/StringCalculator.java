import java.util.ArrayList;
import java.util.List;

/**
 * Calculate sums based on String input.
 *
 * @see <a href="http://osherove.com/tdd-kata-1">A video of this kata. Scroll down for a step-by-step How-to.</a>
 */
public class StringCalculator {

  private final static String[] DELIMITERS = {",", "\n"};

  static private boolean containsDelimiter(final String number) {
    int delimIndex = 0;
    while (delimIndex < DELIMITERS.length) {
      if (number.contains(DELIMITERS[delimIndex])) {
        return true;
      }
      ++delimIndex;
    }
    return false;
  }

  static private int firstHit(final String number) {
    for (int i = 0; i < DELIMITERS.length; ++i) {
      if (number.contains(DELIMITERS[i])) {
        return number.indexOf(DELIMITERS[i]);
      }
    }

    return -1;
  }

  static private List<String> splitNumber(final String number) {
    List<String> numbers = new ArrayList<String>();
    String rest = number;
    int hitPos = 0;

    while (containsDelimiter(rest)) {
      hitPos = firstHit(rest);
      numbers.add(rest.substring(0, hitPos));
      rest = rest.substring(hitPos+1);
    }

    //add last number, not delimiters left
    numbers.add(rest);

    return numbers;
  }

  static int add(final String number) {
    if ("".equals(number) || "0".equals(number)) {
      return 0;
    }

    try {
      if (!containsDelimiter(number)) {
        return Integer.valueOf(number).intValue();
      }

      final List<String> numbers = splitNumber(number);
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
