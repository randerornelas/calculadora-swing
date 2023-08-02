package model;

@FunctionalInterface
public interface MemoryObserver {
    void update(String newValue);
}
