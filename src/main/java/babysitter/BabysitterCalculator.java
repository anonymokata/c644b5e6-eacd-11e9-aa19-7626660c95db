package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = parseTime(startTime);
        int endHour = parseTime(endTime);

        int adjustedStartTime = adjustTimeForEasySubtraction(startHour);
        int adjustedEndTime = adjustTimeForEasySubtraction(endHour);

        int middleNightHours = findMiddleNightHours(adjustedStartTime, adjustedEndTime, family);
        int lateNightHours = findLateNightHours(adjustedStartTime, adjustedEndTime, family);

        int total = calculateTotal(adjustedStartTime, adjustedEndTime, middleNightHours, lateNightHours, family);
        return formatAsDollarValue(total);
    }

    private int parseTime(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        return hour;
    }

    private int adjustTimeForEasySubtraction(int time) {
        int adjustedTime = time;
        if (time < 5) {
            adjustedTime += 12;
        }
        return adjustedTime;
    }

    private int findMiddleNightHours(int adjustedStartTime, int adjustedEndTime, String family) {
        int middleNightHours = 0;
        int timeWhenMiddleRatesStart = 10;
        int timeWhenMiddleRatesEnd = 12;
        if (family.equals("A") || family.equals("C")) {
            return 0;
        }

        for (int i = adjustedEndTime; i > adjustedStartTime; i--) {
            if (i > timeWhenMiddleRatesStart && i <= timeWhenMiddleRatesEnd) {
                middleNightHours++;
            }
        }
        return middleNightHours;
    }

    private int findLateNightHours(int adjustedStartTime, int adjustedEndTime, String family) {
        int lateNightHours = 0;

        int timeWhenRatesChange;
        if (family.equals("A")) {
            timeWhenRatesChange = 11;
        } else if (family.equals("B")){
            timeWhenRatesChange = 12;
        } else {
            timeWhenRatesChange = 9;
        }

        for (int i = adjustedEndTime; i > adjustedStartTime; i--) {
            if (i > timeWhenRatesChange) {
                lateNightHours++;
            }
        }
        return lateNightHours;
    }

    private int calculateTotal(int adjustedStartTime, int adjustedEndTime, int middleNightHours, int lateNightHours, String family) {
        if (family.equals("A")) {
            return ((Math.abs(adjustedEndTime - adjustedStartTime) * 15) + (lateNightHours * 5));
        } else if (family.equals("B")) {
            return ((Math.abs(adjustedEndTime - adjustedStartTime) * 12) + (middleNightHours * -4) + (lateNightHours * 4));
        } else {
            return ((Math.abs(adjustedEndTime - adjustedStartTime) * 21)) + (lateNightHours * -6);
        }
    }

    private String formatAsDollarValue(int total) {
        return "$" + total + ".00";
    }
}