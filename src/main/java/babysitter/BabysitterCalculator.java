package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = Integer.parseInt(startTime.split(":")[0]);
        int endHour = Integer.parseInt(endTime.split(":")[0]);
        int lateNightHours = endHour - 11;
        if (lateNightHours < 0) {
            lateNightHours = 0;
        }
        return "$" + (((endHour - startHour) * 15) + (lateNightHours * 5)) + ".00";
    }
}