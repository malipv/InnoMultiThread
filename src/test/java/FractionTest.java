import ru.inno.task.Fractionable;

public class FractionTest implements Fractionable {
    private int num;
    private int denum;
    private int cnt = 0;

    public FractionTest(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDenum(int denum) {
        this.denum = denum;
    }

    public int getCallCount() {
        return cnt;
    }

    @Override
    public double doubleValue() {
        synchronized (this) {
            cnt++;
        }
        return (double) num / denum;
    }

    @Override
    public double invertedDoubleValue() {
        synchronized (this) {
            cnt++;
        }
        return (double) denum / num;
    }
}
