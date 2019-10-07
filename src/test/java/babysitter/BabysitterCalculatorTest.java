package babysitter;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BabysitterCalculatorTest {

    @Test
    public void testReturnsFifteenDollarsForOneHourWithFamilyA() {
        BabysitterCalculator babysitterCalculator = new BabysitterCalculator();
        Assertions.assertThat(babysitterCalculator.findTotal(21, 22, "A")).isEqualTo(15.00);
    }
}