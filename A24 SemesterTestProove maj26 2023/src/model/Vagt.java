package model;

import java.util.ArrayList;

public class Vagt {

    private int timer;
    private Job job;
    private Frivillig frivillig;

    public Vagt(int timer, Job job) {
        this.timer = timer;
        this.job = job;
    }
    public int getTimer() {
        return timer;
    }

    public void setFrivillig(Frivillig frivillig) {
        if (this.frivillig != frivillig) {
            Frivillig oldFrivillig = this.frivillig;
            if (oldFrivillig != null) {
                frivillig.removeVagt(this);
            }
            this.frivillig = frivillig;
            if (frivillig != null) {
                frivillig.addVagt(this);
            }
        }
    }
    public Frivillig getFrivillig() {
        return frivillig;
    }

    public Job getJob() {
        return job;
    }
}
