import java.util.Set;

/**
 * Calculate sums based on String input.
 *
 * @see <a href="http://osherove.com/tdd-kata-1">A video of this kata. Scroll down for a step-by-step How-to.</a>
 */
public class StringCalculator {

  private final static String NUMBER_DELIMITER = ",";

  static int add(final String number) {
    if ("".equals(number) || "0".equals(number))
      return 0;

    try {
      if (!number.contains(NUMBER_DELIMITER)) {
        return Integer.valueOf(number).intValue();
      }

      final String[] numbers = number.split(NUMBER_DELIMITER);
      int sum = 0;
      for (int i=0 ; i<numbers.length ; ++i)
        sum += Integer.valueOf(numbers[i]).intValue();
      return sum;

    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("This kata is only designed for valid inputs!");
    }
  }
}
