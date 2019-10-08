package babysitter;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BabysitterCalculatorTest {

    @Test
    public void testReturnsFifteenDollarsForOneHourWithFamilyA() {
        BabysitterCalculator babysitterCalculator = new BabysitterCalculator();
        Assertions.assertThat(babysitterCalculator.findTotal("9:00 PM", "10:00 PM", "A")).isEqualTo("$15.00");
    }

    @Test
    public void testReturnsThirtyDollarsForTwoHoursWithFamilyA() {
        BabysitterCalculator babysitterCalculator = new BabysitterCalculator();
        Assertions.assertThat(babysitterCalculator.findTotal("8:00 PM", "10:00 PM", "A")).isEqualTo("$30.00");
    }

}