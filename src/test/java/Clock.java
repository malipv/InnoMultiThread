import ru.inno.task.Clockable;

public class Clock implements Clockable {
    long currentTime;

    public Clock() {
        this.currentTime = System.currentTimeMillis();
    }

    @Override
    public long currentTimeMillis() {
        return this.currentTime;
    }
}
