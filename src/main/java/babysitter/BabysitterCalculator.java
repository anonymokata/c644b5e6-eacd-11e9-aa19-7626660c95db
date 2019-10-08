package babysitter;

public class BabysitterCalculator {

    public String findTotal(String startTime, String endTime, String family) {
        int startHour = Integer.parseInt(startTime.split(":")[0]);
        int endHour = Integer.parseInt(endTime.split(":")[0]);
        return "$" + (endHour - startHour) * 15 + ".00";
    }
}