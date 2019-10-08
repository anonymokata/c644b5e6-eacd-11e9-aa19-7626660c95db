package babysitter;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class BabysitterCalculatorTest {
    BabysitterCalculator babysitterCalculator;

    @Before
    public void setUp() {
        babysitterCalculator = new BabysitterCalculator();
    }

    @Test
    public void testReturnsFifteenDollarsForOneHourWithFamilyA() {
        Assertions.assertThat(babysitterCalculator.findTotal("9:00 PM", "10:00 PM", "A")).isEqualTo("$15.00");
    }

    @Test
    public void testReturnsThirtyDollarsForTwoHoursWithFamilyA() {
        Assertions.assertThat(babysitterCalculator.findTotal("8:00 PM", "10:00 PM", "A")).isEqualTo("$30.00");
    }

    @Test
    public void testReturnsThirtyFiveDollarsForOneHourBeforeElevenPmAndOneHourAfterWithFamilyA() {
        Assertions.assertThat(babysitterCalculator.findTotal("10:00 PM", "12:00 PM", "A")).isEqualTo("$35.00");
    }

    @Test
    public void testReturnsFortyDollarsForTwoLateNightHoursFamilyA() {
        Assertions.assertThat(babysitterCalculator.findTotal("2:00 AM", "4:00 AM", "A")).isEqualTo("$40.00");
    }
}