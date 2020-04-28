package com.hunor.chess.util;

import java.util.LinkedList;
import java.util.List;

public class Property<T> {
    private T value;

    private List<SimpleChangeListener<T>> listeners = new LinkedList<>();

    public Property() {
    }

    public Property(T value) {
        this.value = value;
    }

    public void listen(SimpleChangeListener<T> listener) {
        this.listeners.add(listener);
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
        notifyListeners();
    }

    public void clear() {
        this.set(null);
    }

    public boolean hasValue() {
        return value != null;
    }

    private void notifyListeners() {
        for (SimpleChangeListener<T> listener : listeners) {
            listener.valueChanged(this.value);
        }
    }
}
