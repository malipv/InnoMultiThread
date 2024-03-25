package ru.inno.task;

import ru.inno.task.Clockable;

public interface Cachable <K,V>{
    V getCache(K key, Clockable clock);
    void removeCache(K key);
    void putCache(K key, V value, int lifeTime, Clockable clock);
    void clear();
}
