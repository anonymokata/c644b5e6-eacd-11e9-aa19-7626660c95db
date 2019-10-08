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

    @Test
    public void testReturnsOneHundredNinetyDollarsForFullNightWithFamilyA() {
        Assertions.assertThat(babysitterCalculator.findTotal("5:00 PM", "4:00 AM", "A")).isEqualTo("$190.00");
    }

    @Test
    public void testReturnsFiftyDollarsWorkingNineToMidnightFamilyA() {
        Assertions.assertThat(babysitterCalculator.findTotal("9:00 PM", "12:00 PM", "A")).isEqualTo("$50.00");
    }

    @Test
    public void testReturnsTwelveDollarsForOneEarlyEveningHourFamilyB() {
        Assertions.assertThat(babysitterCalculator.findTotal("5:00 PM", "6:00 PM", "B")).isEqualTo("$12.00");
    }

    @Test
    public void testReturnsTwentyFourDollarsForTwoEarlyEveningHoursFamilyB() {
        Assertions.assertThat(babysitterCalculator.findTotal("5:00 PM", "7:00 PM", "B")).isEqualTo("$24.00");
    }

    @Test
    public void testReturnsEightDollarsForTenToElevenFamilyB() {
        Assertions.assertThat(babysitterCalculator.findTotal("10:00 PM", "11:00 PM", "B")).isEqualTo("$8.00");
    }

    @Test
    public void testReturnsTwentyDollarsForNineToElevenFamilyB() {
        Assertions.assertThat(babysitterCalculator.findTotal("9:00 PM", "11:00 PM", "B")).isEqualTo("$20.00");
    }

    @Test
    public void testReturnsSixteenDollarsForOneHourLateNightFamilyB() {
        Assertions.assertThat(babysitterCalculator.findTotal("1:00 PM", "2:00 AM", "B")).isEqualTo("$16.00");
    }

    @Test
    public void testReturnsTwentyFourDollarsForBetweenElevenAndOneFamilyB() {
        Assertions.assertThat(babysitterCalculator.findTotal("11:00 PM", "1:00 AM", "B")).isEqualTo("$24.00");
    }

    @Test
    public void testReturnsOneHundredFortyDollarsForFullNightFamilyB() {
        Assertions.assertThat(babysitterCalculator.findTotal("5:00 PM", "4:00 AM", "B")).isEqualTo("$140.00");
    }

    @Test
    public void testReturnsTwentyOneDollarsForOneHourFamilyC() {
        Assertions.assertThat(babysitterCalculator.findTotal("5:00 PM", "6:00 PM", "C")).isEqualTo("$21.00");

    }
}