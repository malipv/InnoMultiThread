import ru.inno.task.CacheProxyUtils;
import ru.inno.task.Fractionable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.task.Clockable;

public class MultiThreadTests {
    private FractionTest fraction;
    private Fractionable fractionCached;

    private Clock clock;

    @BeforeEach
    public void init() {
        fraction = new FractionTest(4, 8);
        clock = new Clock();
        fractionCached = CacheProxyUtils.cache(fraction, clock);
    }

    @Test
    public void testUncache() {
        var fractionUncached = fraction;
        fractionUncached.doubleValue();
        fractionUncached.doubleValue();
        Assertions.assertEquals(2, fraction.getCallCount());
    }

    @Test
    public void testCacheWithoutMutator() {
        fractionCached.doubleValue();
        fractionCached.doubleValue();
        Assertions.assertEquals(2, fraction.getCallCount());
    }

    @Test
    public void testCacheWithoutMutatorWithSleep() {
        fractionCached.doubleValue();
        clock.currentTime += 1500L;
        fractionCached.doubleValue();
        Assertions.assertEquals(2, fraction.getCallCount());
    }

    @Test
    public void testCacheWithMutator() {
        fractionCached.doubleValue();
        fractionCached.doubleValue();
        fractionCached.setNum(4);
        fractionCached.doubleValue();
        fractionCached.doubleValue();
        Assertions.assertEquals(4, fraction.getCallCount());
    }

    @Test
    public void testCacheDoubleMethodWithoutMutator() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(4, fraction.getCallCount());
    }

    @Test
    public void testCacheDoubleMethodWithoutMutatorWithSleep() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        clock.currentTime += 1500L;
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(4, fraction.getCallCount());
    }

    @Test
    public void checkCachedDoubleMethodWithMutator() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.setNum(4);
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(8, fraction.getCallCount());
    }

    @Test
    public void testCacheDoubleMethodWithoutMutatorWithValues() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(fraction.doubleValue(), fractionCached.doubleValue());
        Assertions.assertEquals(fraction.invertedDoubleValue(), fractionCached.invertedDoubleValue());
    }

    @Test
    public void testCacheDoubleMethodWithMutatorWithValues() {
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        fractionCached.setNum(4);
        fractionCached.doubleValue();
        fractionCached.invertedDoubleValue();
        Assertions.assertEquals(fraction.doubleValue(), fractionCached.doubleValue());
        Assertions.assertEquals(fraction.invertedDoubleValue(), fractionCached.invertedDoubleValue());
    }
}