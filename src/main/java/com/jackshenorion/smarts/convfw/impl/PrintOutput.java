package com.jackshenorion.smarts.convfw.impl;

import com.jackshenorion.smarts.convfw.ProcessOutput;

import java.util.logging.Logger;

public class PrintOutput implements ProcessOutput{
    Logger logger = Logger.getLogger(PrintOutput.class.getName());

    @Override
    public void write(Object obj) {
        logger.info("Write PrintOutput: " + obj);
    }

    @Override
    public void open() {
        logger.info("PrintOutput opened!");

    }

    @Override
    public void close() {
        logger.info("PrintOutput closed");
    }
}
