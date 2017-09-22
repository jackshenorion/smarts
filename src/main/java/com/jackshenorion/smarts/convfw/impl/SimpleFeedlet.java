package com.jackshenorion.smarts.convfw.impl;

import com.jackshenorion.smarts.convfw.FeedletBase;

import java.util.Arrays;
import java.util.logging.Logger;

public class SimpleFeedlet extends FeedletBase{
    Logger logger = Logger.getLogger(SimpleFeedlet.class.getName());

    @Override
    protected void finish() {
        logger.info("All input has been processed, finish!");
    }

    @Override
    protected void onMessage(Object obj) {
        logger.info("Process message: " + obj);
    }

    public static void main(String[] args) {
        BatchInput batchInput = new BatchInput();
        batchInput.setInputs(Arrays.asList(
                new ArrayInput(),
                new ArrayInput(),
                new ArrayInput()
        ));
        FeedletBase feedlet = new SimpleFeedlet();
        feedlet.setInput(batchInput);
        feedlet.setOutput(new PrintOutput());
        feedlet.run();
    }
}
