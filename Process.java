// יובל חגבי - 207797424
// חן שלו - 313584906
package test;
import java.util.Objects;

public class Process {
    private int arrivalTime;
    private int computationTime;
    private int completeTime;
    private int needTime;
    private int TA;
    private boolean done;

    public Process(int arrivalTime, int computationTime, int TA) {
        this.arrivalTime = arrivalTime;
        this.computationTime = computationTime;
        this.completeTime=0;
        this.needTime=computationTime;
        this.TA = TA;
        this.done=false;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getComputationTime() {
        return computationTime;
    }
    public int getTA() {
        return TA;
    }
    public void setTA(int TA) {
        this.TA = TA;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public int getCompleteTime() {
        return completeTime;
    }
    public void setCompleteTime(int completeTime) {
        this.completeTime = completeTime;
    }
    public int getNeedTime() {
        return needTime;
    }
    public void setNeedTime(int needTime) {
        this.needTime = needTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Process)) return false;
        Process process = (Process) o;
        return getArrivalTime() == process.getArrivalTime() && getComputationTime() == process.getComputationTime() && getTA() == process.getTA() && isDone() == process.isDone();
    }
}
