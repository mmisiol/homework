import com.szalkowm.homework.application.time.TimeRange;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;

import static java.time.LocalTime.parse;

@RunWith(Parameterized.class)
public class TimeRangeTest {

    private final LocalTime from;
    private final LocalTime to;
    private final LocalTime tested;
    private final boolean expected;

    public TimeRangeTest(LocalTime from, LocalTime to, LocalTime tested, boolean expected) {
        this.from = from;
        this.to = to;
        this.tested = tested;
        this.expected = expected;
    }


    @Test
    public void testInRangeCheck() {
        TimeRange timeRange = new TimeRange(this.from, this.to);
        Assert.assertEquals(expected, timeRange.isInRange(this.tested));
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {parse("10:00:00"), parse("11:00:00"), parse("10:30:00"), true},
                {parse("10:00:00"), parse("11:00:00"), parse("10:00:00"), true},
                {parse("10:00:00"), parse("11:00:00"), parse("10:00:00").minusNanos(1), false},
                {parse("10:00:00"), parse("11:00:00"), parse("11:00:00"), true},
                {parse("10:00:00"), parse("11:00:00"), parse("11:00:00").plusNanos(1), false},

                {parse("23:00:00"), parse("02:00:00"), parse("01:00:00"), true},
                {parse("23:00:00"), parse("02:00:00"), parse("23:00:00"), true},
                {parse("23:00:00"), parse("02:00:00"), parse("23:00:00").minusNanos(1), false},
                {parse("23:00:00"), parse("02:00:00"), parse("02:00:00"), true},
                {parse("23:00:00"), parse("02:00:00"), parse("02:00:00").plusNanos(1), false},

                {parse("23:00:00"), parse("02:00:00"), parse("00:00:00"), true},
                {parse("23:00:00"), parse("02:00:00"), parse("00:00:00").plusNanos(1), true},
                {parse("23:00:00"), parse("02:00:00"), parse("00:00:00").minusNanos(1), true},

                {parse("00:00:00"), parse("06:00:00"), parse("00:00:00"), true},
                {parse("00:00:00"), parse("06:00:00"), parse("00:00:00").minusNanos(1), false},

                {parse("07:00:00"), parse("07:00:00"), parse("07:00:00"), true},
                {parse("07:00:00"), parse("07:00:00"), parse("07:00:00").minusNanos(1), false},
                {parse("07:00:00"), parse("07:00:00"), parse("07:00:00").plusNanos(1), false}
        });
    }

}
