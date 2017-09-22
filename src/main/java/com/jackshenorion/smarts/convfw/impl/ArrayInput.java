package com.jackshenorion.smarts.convfw.impl;

import com.jackshenorion.smarts.convfw.ProcessInput;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ArrayInput implements ProcessInput {
    Logger logger = Logger.getLogger(ArrayInput.class.getName());

    private List<String> data = Arrays.asList(
            "Record A",
            "Record B",
            "Record C"
    );
    private int next = 0;

    @Override
    public boolean hasNext() {
        return next < data.size();
    }

    @Override
    public Object next() {
        return data.get(next++);
    }

    @Override
    public void open() {
        next = 0;
    }

    @Override
    public void close() {
        logger.info("ArrayInput closed!");
    }
}
