package com.jackshenorion.smarts.convfw.impl;

import com.jackshenorion.smarts.convfw.ProcessInput;

import java.util.List;

public class BatchInput implements ProcessInput{
    private List<ProcessInput> inputs;

    private int nextInputIndex = 0;
    private ProcessInput currentInput = null;

    public void setInputs(List<ProcessInput> inputs) {
        this.inputs = inputs;
    }

    @Override
    public boolean hasNext() {
        if (inputs.size() == 0) {
            return false;
        }

        if (getCurrentInput().hasNext()) {
            return true;
        }

        while (hasNextInput()) {
            moveToNextInput();
            if (getCurrentInput().hasNext()) {
                return true;
            }
        }

        return false;
    }

    private ProcessInput getCurrentInput() {
        if (currentInput == null) {
            moveToNextInput();
        }
        return currentInput;
    }

    private void moveToNextInput() {
        currentInput = inputs.get(nextInputIndex++);
    }

    private boolean hasNextInput() {
        return nextInputIndex < inputs.size();
    }

    @Override
    public Object next() {
        return getCurrentInput().next();
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }
}
