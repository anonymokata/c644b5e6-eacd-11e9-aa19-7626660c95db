package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = parseTime(startTime);
        int endHour = parseTime(endTime);

        int startAtMilitaryTime = convertTimeToMilitary(startHour);
        int endAtMilitaryTime = convertTimeToMilitary(endHour);

        int lateNightHours = findLateNightHours(startAtMilitaryTime, endAtMilitaryTime);

        return "$" + calculateTotal(endHour, startHour, lateNightHours) + ".00";

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

        for (int i = startTimeInMilitary; i < endTimeInMilitary; i++) {
            if (i < 5 || i > 22) {
                lateNightHours++;
            }
        }

        return lateNightHours;
    }

    private int calculateTotal( int endHour, int startHour, int lateNightHours) {
        return ((endHour - startHour) * 15) + (lateNightHours * 5);
    }
}