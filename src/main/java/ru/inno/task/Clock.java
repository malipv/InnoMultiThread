package ru.inno.task;

import ru.inno.task.Clockable;

public class Clock implements Clockable {
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
