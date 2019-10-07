package babysitter;

public class BabysitterCalculator {

    public double findTotal(int startTime, int endTime, String family) {
        return (endTime - startTime) * 15.00;
    }
}
