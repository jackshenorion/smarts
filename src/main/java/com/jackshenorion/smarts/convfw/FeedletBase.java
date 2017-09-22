package com.jackshenorion.smarts.convfw;

public abstract class FeedletBase {
    ProcessInput input;
    ProcessOutput output;

    public void setInput(ProcessInput input) {
        this.input = input;
    }

    public void setOutput(ProcessOutput output) {
        this.output = output;
    }

    public void run() {
        input.open();
        output.open();
        poll();
    }
    
    public void poll() {

        while(input.hasNext()) {
            Object obj = input.next();
            onMessage(obj);
            output.write(obj);
        }
        input.close();
        output.close();
        finish();
    }

    protected abstract void finish();
    protected abstract void onMessage(Object obj);

}
