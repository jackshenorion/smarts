package com.jackshenorion.smarts.util.mapper;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class MapBinding<T> {

    private Function<T, String> sourceGetter;
    private BiConsumer<T, String> targetSetter;

    public MapBinding(Function<T, String> sourceGetter, BiConsumer<T, String> targetSetter) {
        this.sourceGetter = sourceGetter;
        this.targetSetter = targetSetter;
    }

    public String get(T msg) {
        return sourceGetter.apply(msg);
    }

    public void set(T msg, String newValue) {
        targetSetter.accept(msg, newValue);
    }
}
