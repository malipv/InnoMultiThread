package ru.inno.task;

import lombok.Setter;
import ru.inno.task.Clockable;

class CacheValue {
    private final Object value;
    @Setter
    private long createCacheTime;
    private final int cacheLifeTimeMillis;

    CacheValue(Object value, int lifeTime, Clockable clock) {
        this.value = value;
        this.cacheLifeTimeMillis = lifeTime;
        this.createCacheTime = clock.currentTimeMillis();
    }
    Object getValue(Clockable clock) {
        return value;
    }
    boolean isActual(Clockable clock){
        return (clock.currentTimeMillis() - createCacheTime < cacheLifeTimeMillis);
    }
}