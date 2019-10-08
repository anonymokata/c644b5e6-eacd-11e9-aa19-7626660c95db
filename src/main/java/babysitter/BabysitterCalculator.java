package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = parseTime(startTime);
        int endHour = parseTime(endTime);

        int startAtMilitaryTime = convertTimeToMilitary(startHour);
        int endAtMilitaryTime = convertTimeToMilitary(endHour);

        int lateNightHours = findLateNightHours(startAtMilitaryTime, endAtMilitaryTime);

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

    private int findLateNightHours (int startTimeInMilitary, int endTimeInMilitary) {
        int lateNightHours = 0;
        int adjustedStart = startTimeInMilitary;
        int adjustedEnd = endTimeInMilitary;

        if (startTimeInMilitary < 17) {
            adjustedStart += 24;
        }

        if (endTimeInMilitary <= 17) {
            adjustedEnd += 24;
        }


        for (int i = adjustedEnd; i > adjustedStart; i--) {
            if (i > 23) {
                lateNightHours++;
            }
        }

        return lateNightHours;
    }

    private int calculateTotal( int endHour, int startHour, int lateNightHours, String family) {

        if (family.equals("A")) {
            int adjustedStart = startHour;
            int adjustedEnd = endHour;
            if (endHour <= 17) {
                adjustedEnd += 24;
            }

            if (startHour < 17) {
                adjustedStart += 24;
            }

            return ((Math.abs(adjustedEnd - adjustedStart) * 15) + (lateNightHours * 5));
        }
        else {
            if (startHour < 22) {
                return (endHour - startHour) * 12;
            }
            return (endHour - startHour) * 8;
        }
    }
}