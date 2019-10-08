package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = Integer.parseInt(startTime.split(":")[0]);
        int endHour = Integer.parseInt(endTime.split(":")[0]);

        int startAtMilitaryTime;
        if (startHour < 5) {
            startAtMilitaryTime = startHour;
        }
        else {
            startAtMilitaryTime = startHour + 12;
        }

        int endAtMilitaryTime;
        if (endHour < 5) {
            endAtMilitaryTime = endHour;
        }
        else {
            endAtMilitaryTime = endHour + 12;
        }

        int lateNightHours = 0;
        for (int i = startAtMilitaryTime; i < endAtMilitaryTime; i++) {
            if (i < 5 || i > 22) {
                lateNightHours++;
            }
        }

        return "$" + (((endHour - startHour) * 15) + (lateNightHours * 5)) + ".00";
    }
}