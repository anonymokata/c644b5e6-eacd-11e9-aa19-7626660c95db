package babysitter;

public class BabysitterCalculator {

    public static void main(String[] args) {
        System.out.println(new BabysitterCalculator().findTotal(args[0], args[1], args[2]));
    }

    public String findTotal(String startTime, String endTime, String family) {
        String validationResult = validateInput(startTime, endTime);
        if (!validationResult.equals("valid")) {
            return validationResult;
        }

        int startHour = parseTime(startTime);
        int endHour = addHourToEndTimeIfExtraMinutes(endTime);

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

    private boolean validateTimeFormat(String time) {
        if (time.matches("[0-9]{1,2}:[0-9]{1,2}[AP]M")) {
            return true;
        }
        return false;
    }

    private boolean validateWorkingHours(String time) {
        String AmOrPm = time.substring(time.length() - 2);
        int hour = parseTime(time);
        if (AmOrPm.equals("AM")) {
            if (hour > 4) {
                return false;
            }
        } else if (AmOrPm.equals("PM")) {
            if (hour < 5) {
                return false;
            }
        }
        return true;
    }

    private boolean validateStartTimeBeforeEndTime(String startTime, String endTime) {
        int adjustedStartTime = adjustTimeForEasySubtraction(parseTime(startTime));
        int adjustedEndTime = adjustTimeForEasySubtraction(parseTime(endTime));
        if (adjustedEndTime - adjustedStartTime <= 0) {
            return false;
        }
        return true;
    }

    private int adjustTimeForEasySubtraction(int time) {
        int adjustedTime = time;
        if (time < 5) {
            adjustedTime += 12;
        }
        return adjustedTime;
    }

    public String validateInput(String startTime, String endTime) {
        if (!validateTimeFormat(startTime) || !validateTimeFormat(endTime)) {
            return "Sorry, the valid time format is XX:XXPM.";
        }

        if (!validateWorkingHours(startTime) || !validateWorkingHours(endTime)) {
            return "Sorry, valid working hours are between 5:00PM and 4:00AM.";
        }

        if (!validateStartTimeBeforeEndTime(startTime, endTime)) {
            return "Sorry, your start time must be before your end time.";
        }
        return "valid";
    }

    private int addHourToEndTimeIfExtraMinutes(String endTime) {
        int actualEndTime = parseTime(endTime);
        if (endTime.substring(endTime.length() - 4, endTime.length() - 2).equals("00")) {
            return actualEndTime;
        } else {
            return actualEndTime + 1;
        }
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

    private int findDefaultRate(String family) {
        int defaultRate;
        if (family.equals("A")) {
            defaultRate = 15;
        } else if (family.equals("B")) {
            defaultRate = 12;
        } else if (family.equals("C")) {
            defaultRate = 21;
        } else {
            defaultRate = 0;
        }
        return defaultRate;
    }

    private int findMiddleRate(String family) {
        int middleRate;
        if (family.equals("B")) {
            middleRate = -4;
        } else {
            middleRate = 0;
        }
        return middleRate;
    }

    private int findLateRate(String family) {
        int lateRate;
        if (family.equals("A")) {
            lateRate = 5;
        } else if (family.equals("B")) {
            lateRate = 4;
        } else if (family.equals("C")) {
            lateRate = -6;
        } else {
            lateRate = 0;
        }
        return lateRate;
    }

    private int calculateTotal(int adjustedStartTime, int adjustedEndTime, int middleNightHours, int lateNightHours, String family) {
        int defaultRate = findDefaultRate(family);
        int middleRateModifier = findMiddleRate(family);
        int lateRateModifier = findLateRate(family);

        return ((Math.abs(adjustedEndTime - adjustedStartTime) * defaultRate) + (middleNightHours * middleRateModifier)
                + (lateNightHours * lateRateModifier));
    }

    private String formatAsDollarValue(int total) {
        return "$" + total + ".00";
    }
}