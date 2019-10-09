package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = parseTime(startTime);
        int endHour = parseTime(endTime);

        int middleNightHours = findMiddleNightHours(startHour, endHour, family);
        int lateNightHours = findLateNightHours(startHour, endHour, family);

        int total = calculateTotal(startHour, endHour, middleNightHours, lateNightHours, family);
        return formatAsDollarValue(total);
    }

    private int parseTime(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        return hour;
    }

    private int findMiddleNightHours(int startHour, int endHour, String family) {
        int middleNightHours = 0;
        int timeWhenMiddleRatesStart = 10;
        int timeWhenMiddleRatesEnd = 12;
        if (family.equals("A") || family.equals("C")) {
            return 0;
        }

        int adjustedStart = startHour;
        int adjustedEnd = endHour;

        if (startHour < 5) {
            adjustedStart += 12;
        }

        if (endHour <= 5) {
            adjustedEnd += 12;
        }

        for (int i = adjustedEnd; i > adjustedStart; i--) {
            if (i > timeWhenMiddleRatesStart && i <= timeWhenMiddleRatesEnd) {
                middleNightHours++;
            }
        }
        return middleNightHours;
    }

    private int findLateNightHours(int startHours, int endHours, String family) {
        int lateNightHours = 0;

        int timeWhenRatesChange;
        if (family.equals("A")) {
            timeWhenRatesChange = 11;
        } else if (family.equals("B")){
            timeWhenRatesChange = 12;
        } else {
            timeWhenRatesChange = 9;
        }

        int adjustedStart = startHours;
        int adjustedEnd = endHours;

        if (startHours < 5) {
            adjustedStart += 12;
        }

        if (endHours <= 5) {
            adjustedEnd += 12;
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
        if (endHour <= 5) {
            adjustedEnd += 12;
        }

        if (startHour < 5) {
            adjustedStart += 12;
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