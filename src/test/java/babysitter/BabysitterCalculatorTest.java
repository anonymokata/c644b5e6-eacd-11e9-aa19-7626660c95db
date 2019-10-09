package babysitter;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class BabysitterCalculatorTest {
    BabysitterCalculator babysitterCalculator;

    @Before
    public void setUp() {
        babysitterCalculator = new BabysitterCalculator();
    }

    @Test
    public void testReturnsFifteenDollarsForOneHourWithFamilyA() {
        assertThat(babysitterCalculator.findTotal("9:00 PM", "10:00 PM", "A")).isEqualTo("$15.00");
    }

    @Test
    public void testReturnsThirtyDollarsForTwoHoursWithFamilyA() {
        assertThat(babysitterCalculator.findTotal("8:00 PM", "10:00 PM", "A")).isEqualTo("$30.00");
    }

    @Test
    public void testReturnsThirtyFiveDollarsForOneHourBeforeElevenPmAndOneHourAfterWithFamilyA() {
        assertThat(babysitterCalculator.findTotal("10:00 PM", "12:00 PM", "A")).isEqualTo("$35.00");
    }

    @Test
    public void testReturnsFortyDollarsForTwoLateNightHoursFamilyA() {
        assertThat(babysitterCalculator.findTotal("2:00 AM", "4:00 AM", "A")).isEqualTo("$40.00");
    }

    @Test
    public void testReturnsOneHundredNinetyDollarsForFullNightWithFamilyA() {
        assertThat(babysitterCalculator.findTotal("5:00 PM", "4:00 AM", "A")).isEqualTo("$190.00");
    }

    @Test
    public void testReturnsFiftyDollarsWorkingNineToMidnightFamilyA() {
        assertThat(babysitterCalculator.findTotal("9:00 PM", "12:00 PM", "A")).isEqualTo("$50.00");
    }

    @Test
    public void testReturnsTwelveDollarsForOneEarlyEveningHourFamilyB() {
        assertThat(babysitterCalculator.findTotal("5:00 PM", "6:00 PM", "B")).isEqualTo("$12.00");
    }

    @Test
    public void testReturnsTwentyFourDollarsForTwoEarlyEveningHoursFamilyB() {
        assertThat(babysitterCalculator.findTotal("5:00 PM", "7:00 PM", "B")).isEqualTo("$24.00");
    }

    @Test
    public void testReturnsEightDollarsForTenToElevenFamilyB() {
        assertThat(babysitterCalculator.findTotal("10:00 PM", "11:00 PM", "B")).isEqualTo("$8.00");
    }

    @Test
    public void testReturnsTwentyDollarsForNineToElevenFamilyB() {
        assertThat(babysitterCalculator.findTotal("9:00 PM", "11:00 PM", "B")).isEqualTo("$20.00");
    }

    @Test
    public void testReturnsSixteenDollarsForOneHourLateNightFamilyB() {
        assertThat(babysitterCalculator.findTotal("1:00 PM", "2:00 AM", "B")).isEqualTo("$16.00");
    }

    @Test
    public void testReturnsTwentyFourDollarsForBetweenElevenAndOneFamilyB() {
        assertThat(babysitterCalculator.findTotal("11:00 PM", "1:00 AM", "B")).isEqualTo("$24.00");
    }

    @Test
    public void testReturnsOneHundredFortyDollarsForFullNightFamilyB() {
        assertThat(babysitterCalculator.findTotal("5:00 PM", "4:00 AM", "B")).isEqualTo("$140.00");
    }

    @Test
    public void testReturnsTwentyOneDollarsForOneHourFamilyC() {
        assertThat(babysitterCalculator.findTotal("5:00 PM", "6:00 PM", "C")).isEqualTo("$21.00");
    }

    @Test
    public void testReturnsFortyTwoDollarsForTwoHoursFamilyC() {
        assertThat(babysitterCalculator.findTotal("5:00 PM", "7:00 PM", "C")).isEqualTo("$42.00");
    }

    @Test
    public void testReturnsFifteenDollarsForOneLateNightHourFamilyC() {
        assertThat(babysitterCalculator.findTotal("10:00 PM", "11:00 PM", "C")).isEqualTo("$15.00");
    }

    @Test
    public void testReturnsThirtySixDollarsForOneStandardHourAndOneLateHourFamilyC() {
        assertThat(babysitterCalculator.findTotal("8:00 PM", "10:00 PM", "C")).isEqualTo("$36.00");
    }

    @Test
    public void testReturnsTwoHundredSevenForOneNightFamilyC() {
        assertThat(babysitterCalculator.findTotal("5:00 PM", "4:00 AM", "C")).isEqualTo("$189.00");
    }
}