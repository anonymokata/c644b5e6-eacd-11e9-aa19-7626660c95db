package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = parseTime(startTime);
        int endHour = parseTime(endTime);

        int startAtMilitaryTime = convertTimeToMilitary(startHour);
        int endAtMilitaryTime = convertTimeToMilitary(endHour);

        int lateNightHours = findLateNightHours(startAtMilitaryTime, endAtMilitaryTime, family);

        return "$" + calculateTotal(endAtMilitaryTime, startAtMilitaryTime, lateNightHours, family) + ".00";

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

    private int findLateNightHours (int startTimeInMilitary, int endTimeInMilitary, String family) {
        int lateNightHours = 0;
        int adjustedStart = startTimeInMilitary;
        int adjustedEnd = endTimeInMilitary;
        int timeWhenRatesChange = 0;
        if (family.equals("A")) {
            timeWhenRatesChange = 23;
        }
        else {
            timeWhenRatesChange = 22;
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

    private int calculateTotal( int endHour, int startHour, int lateNightHours, String family) {
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
        else {
            return ((Math.abs(adjustedEnd - adjustedStart) * 12) + (lateNightHours * -4));
        }
    }
}