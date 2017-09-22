package com.jackshenorion.smarts.convfw;

public interface ProcessOutput {
    void write(Object obj);

    void open();

    void close();
}
