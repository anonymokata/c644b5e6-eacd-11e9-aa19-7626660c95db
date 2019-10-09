package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = parseTime(startTime);
        int endHour = parseTime(endTime);

        int startAtMilitaryTime = convertTimeToMilitary(startHour);
        int endAtMilitaryTime = convertTimeToMilitary(endHour);

        int middleNightHours = findMiddleNightHours(startAtMilitaryTime, endAtMilitaryTime, family);
        int lateNightHours = findLateNightHours(startAtMilitaryTime, endAtMilitaryTime, family);

        int total = calculateTotal(startAtMilitaryTime, endAtMilitaryTime, middleNightHours, lateNightHours, family);
        return formatAsDollarValue(total);
    }

    private int parseTime(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        return hour;
    }

    private int convertTimeToMilitary(int hour) {
        int militaryTime;
        if (hour < 5) {
            militaryTime = hour;
        } else {
            militaryTime = hour + 12;
        }
        return militaryTime;
    }

    private int findMiddleNightHours(int startTimeInMilitary, int endTimeInMilitary, String family) {
        int middleNightHours = 0;
        int timeWhenMiddleRatesStart = 22;
        int timeWhenMiddleRatesEnd = 24;
        if (family.equals("A") || family.equals("C")) {
            return 0;
        }

        int adjustedStart = startTimeInMilitary;
        int adjustedEnd = endTimeInMilitary;

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

        int timeWhenRatesChange;
        if (family.equals("A")) {
            timeWhenRatesChange = 23;
        } else if (family.equals("B")){
            timeWhenRatesChange = 24;
        } else {
            timeWhenRatesChange = 21;
        }

        int adjustedStart = startTimeInMilitary;
        int adjustedEnd = endTimeInMilitary;

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

    private int calculateTotal(int startHour, int endHour, int middleNightHours, int lateNightHours, String family) {
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
        } else if (family.equals("B")) {
            return ((Math.abs(adjustedEnd - adjustedStart) * 12) + (middleNightHours * -4) + (lateNightHours * 4));
        } else {
            return ((Math.abs(adjustedEnd - adjustedStart) * 21)) + (lateNightHours * -6);
        }
    }

    private String formatAsDollarValue(int total) {
        return "$" + total + ".00";
    }
}