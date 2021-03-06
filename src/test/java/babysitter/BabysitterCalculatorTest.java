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
        assertThat(babysitterCalculator.findTotal("9:00PM", "10:00PM", "A")).isEqualTo("$15.00");
    }

    @Test
    public void testReturnsThirtyDollarsForTwoHoursWithFamilyA() {
        assertThat(babysitterCalculator.findTotal("8:00PM", "10:00PM", "A")).isEqualTo("$30.00");
    }

    @Test
    public void testReturnsThirtyFiveDollarsForOneHourBeforeElevenPmAndOneHourAfterWithFamilyA() {
        assertThat(babysitterCalculator.findTotal("10:00PM", "12:00PM", "A")).isEqualTo("$35.00");
    }

    @Test
    public void testReturnsFortyDollarsForTwoLateNightHoursFamilyA() {
        assertThat(babysitterCalculator.findTotal("2:00AM", "4:00AM", "A")).isEqualTo("$40.00");
    }

    @Test
    public void testReturnsOneHundredNinetyDollarsForFullNightWithFamilyA() {
        assertThat(babysitterCalculator.findTotal("5:00PM", "4:00AM", "A")).isEqualTo("$190.00");
    }

    @Test
    public void testReturnsFiftyDollarsWorkingNineToMidnightFamilyA() {
        assertThat(babysitterCalculator.findTotal("9:00PM", "12:00PM", "A")).isEqualTo("$50.00");
    }

    @Test
    public void testReturnsTwelveDollarsForOneEarlyEveningHourFamilyB() {
        assertThat(babysitterCalculator.findTotal("5:00PM", "6:00PM", "B")).isEqualTo("$12.00");
    }

    @Test
    public void testReturnsTwentyFourDollarsForTwoEarlyEveningHoursFamilyB() {
        assertThat(babysitterCalculator.findTotal("5:00PM", "7:00PM", "B")).isEqualTo("$24.00");
    }

    @Test
    public void testReturnsEightDollarsForTenToElevenFamilyB() {
        assertThat(babysitterCalculator.findTotal("10:00PM", "11:00PM", "B")).isEqualTo("$8.00");
    }

    @Test
    public void testReturnsTwentyDollarsForNineToElevenFamilyB() {
        assertThat(babysitterCalculator.findTotal("9:00PM", "11:00PM", "B")).isEqualTo("$20.00");
    }

    @Test
    public void testReturnsSixteenDollarsForOneHourLateNightFamilyB() {
        assertThat(babysitterCalculator.findTotal("1:00AM", "2:00AM", "B")).isEqualTo("$16.00");
    }

    @Test
    public void testReturnsTwentyFourDollarsForBetweenElevenAndOneFamilyB() {
        assertThat(babysitterCalculator.findTotal("11:00PM", "1:00AM", "B")).isEqualTo("$24.00");
    }

    @Test
    public void testReturnsOneHundredFortyDollarsForFullNightFamilyB() {
        assertThat(babysitterCalculator.findTotal("5:00PM", "4:00AM", "B")).isEqualTo("$140.00");
    }

    @Test
    public void testReturnsTwentyOneDollarsForOneHourFamilyC() {
        assertThat(babysitterCalculator.findTotal("5:00PM", "6:00PM", "C")).isEqualTo("$21.00");
    }

    @Test
    public void testReturnsFortyTwoDollarsForTwoHoursFamilyC() {
        assertThat(babysitterCalculator.findTotal("5:00PM", "7:00PM", "C")).isEqualTo("$42.00");
    }

    @Test
    public void testReturnsFifteenDollarsForOneLateNightHourFamilyC() {
        assertThat(babysitterCalculator.findTotal("10:00PM", "11:00PM", "C")).isEqualTo("$15.00");
    }

    @Test
    public void testReturnsThirtySixDollarsForOneStandardHourAndOneLateHourFamilyC() {
        assertThat(babysitterCalculator.findTotal("8:00PM", "10:00PM", "C")).isEqualTo("$36.00");
    }

    @Test
    public void testReturnsTwoHundredSevenForOneNightFamilyC() {
        assertThat(babysitterCalculator.findTotal("5:00PM", "4:00AM", "C")).isEqualTo("$189.00");
    }

    @Test
    public void testReturnsErrorMessageForBadStartTimeFormat() {
        assertThat(babysitterCalculator.findTotal("2 AM", "3:00AM", "A")).isEqualTo
                ("Sorry, the valid time format is XX:XXPM.");
    }

    @Test
    public void testReturnsErrorMessageForBadEndTimeFormat() {
        assertThat(babysitterCalculator.findTotal("2:00AM", "3AM", "A")).isEqualTo
                ("Sorry, the valid time format is XX:XXPM.");
    }

    @Test
    public void testReturnsErrorMessageForNonsenseTimeOfDay() {
        assertThat(babysitterCalculator.findTotal("11:00NM", "6:00PM", "A")).isEqualTo
                ("Sorry, the valid time format is XX:XXPM.");
    }

    @Test
    public void testReturnsErrorMessageForStartTimeOutsideOfWorkingHours() {
        assertThat(babysitterCalculator.findTotal("11:00AM", "6:00PM", "A")).isEqualTo
                ("Sorry, valid working hours are between 5:00PM and 4:00AM.");
    }

    @Test
    public void testReturnsErrorMessageForEndTimeOutsideOfWorkingHours() {
        assertThat(babysitterCalculator.findTotal("4:00AM", "2:00PM", "A")).isEqualTo
                ("Sorry, valid working hours are between 5:00PM and 4:00AM.");
    }

    @Test
    public void testReturnsErrorMessageForEndTimeBeforeStartTime() {
        assertThat(babysitterCalculator.findTotal("4:00AM", "3:00AM", "A")).isEqualTo
                ("Sorry, your start time must be before your end time.");
    }

    @Test
    public void testReturnsErrorMessageForEndTimeEqualToStartTime() {
        assertThat(babysitterCalculator.findTotal("7:00PM", "7:00PM", "A")).isEqualTo
                ("Sorry, your start time must be before your end time.");
    }

    @Test
    public void testSitterGetsPaidForEntireStartHour() {
        assertThat(babysitterCalculator.findTotal("5:50PM", "7:00PM", "B")).isEqualTo("$24.00");
    }

    @Test
    public void testSitterGetsPaidForEntireEndHour() {
        assertThat(babysitterCalculator.findTotal("6:00PM", "8:30PM", "B")).isEqualTo("$36.00");
    }

    @Test
    public void testSitterGetsPaidForProperHoursWhenBothStartAndEndHAveMinutes() {
        assertThat(babysitterCalculator.findTotal("6:12PM", "8:30PM", "B")).isEqualTo("$36.00");
    }

    @Test
    public void testReturnsErrorMessageWhenFamilyIsInvalid() {
        assertThat(babysitterCalculator.findTotal("6:00PM", "8:00PM", "D")).isEqualTo
                ("Sorry, the only valid families are A, B, or C.");
    }

    @Test
    public void testReturnsErrorMessageWhenFamilyIsInvalidAgain() {
        assertThat(babysitterCalculator.findTotal("6:00PM", "8:00PM", "Invalid family.")).isEqualTo
                ("Sorry, the only valid families are A, B, or C.");
    }
}