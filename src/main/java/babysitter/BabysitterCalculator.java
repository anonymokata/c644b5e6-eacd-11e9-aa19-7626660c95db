package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = parseTime(startTime);
        int endHour = parseTime(endTime);

        int startAtMilitaryTime = convertTimeToMilitary(startHour);
        int endAtMilitaryTime = convertTimeToMilitary(endHour);

        int middleNightHours = findMiddleNightHours(startAtMilitaryTime, endAtMilitaryTime, family);
        int lateNightHours = findLateNightHours(startAtMilitaryTime, endAtMilitaryTime, family);

        return "$" + calculateTotal(endAtMilitaryTime, startAtMilitaryTime, middleNightHours, lateNightHours, family) + ".00";

    }

    private int parseTime(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        return hour;
    }

    private int convertTimeToMilitary(int hour) {
        int militaryTime;
        if (hour < 5) {
            militaryTime = hour;
        }
        else {
            militaryTime = hour + 12;
        }
        return militaryTime;
    }

    private int findMiddleNightHours(int startTimeInMilitary, int endTimeInMilitary, String family) {
        int middleNightHours = 0;
        int adjustedStart = startTimeInMilitary;
        int adjustedEnd = endTimeInMilitary;
        int timeWhenMiddleRatesStart = 22;
        int timeWhenMiddleRatesEnd = 24;
        if (family.equals("A")) {
            return 0;
        }

        if (startTimeInMilitary < 17) {
            adjustedStart += 24;
        }

        if (endTimeInMilitary <= 17) {
            adjustedEnd += 24;
        }

        for (int i = adjustedEnd; i > adjustedStart; i--) {
            if (i > timeWhenMiddleRatesStart && i <= timeWhenMiddleRatesEnd) {
                middleNightHours++;
            }
        }
        return middleNightHours;
    }

    private int findLateNightHours(int startTimeInMilitary, int endTimeInMilitary, String family) {
        int lateNightHours = 0;
        int adjustedStart = startTimeInMilitary;
        int adjustedEnd = endTimeInMilitary;
        int timeWhenRatesChange = 0;
        if (family.equals("A")) {
            timeWhenRatesChange = 23;
        }
        else {
            timeWhenRatesChange = 24;
        }

        if (startTimeInMilitary < 17) {
            adjustedStart += 24;
        }

        if (endTimeInMilitary <= 17) {
            adjustedEnd += 24;
        }

        for (int i = adjustedEnd; i > adjustedStart; i--) {
            if (i > timeWhenRatesChange) {
                lateNightHours++;
            }
        }
        return lateNightHours;
    }

    private int calculateTotal( int endHour, int startHour, int middleNightHours, int lateNightHours, String family) {
        int adjustedStart = startHour;
        int adjustedEnd = endHour;
        if (endHour <= 17) {
            adjustedEnd += 24;
        }

        if (startHour < 17) {
            adjustedStart += 24;
        }
        if (family.equals("A")) {
            return ((Math.abs(adjustedEnd - adjustedStart) * 15) + (lateNightHours * 5));
        }
        else if (family.equals("B")) {
            return ((Math.abs(adjustedEnd - adjustedStart) * 12) + (middleNightHours * -4) + (lateNightHours * 4));
        }
        else {
            return 21;
        }
    }
}