package com.jackshenorion.smarts.convfw;

public interface ProcessInput {
    boolean hasNext();

    Object next();

    void open();

    void close();
}
