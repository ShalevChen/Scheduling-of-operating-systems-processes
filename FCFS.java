
package test;
import java.util.ArrayList;
import java.util.Collections;

public class FCFS implements Timings{
    @Override
    public void solution(ArrayList<Process> originalList) {
        ArrayList<Process> list=new ArrayList<Process>(originalList.size());
        for(int i=0; i<originalList.size(); i++){
            Process p=originalList.get(i);
            Process newp=new Process(p.getArrivalTime(),p.getComputationTime(),p.getTA());
            list.add(newp);
        }
        double sum=0;
        Collections.sort(list,(p1, p2)->p1.getArrivalTime()-p2.getArrivalTime());
        int clock= list.get(0).getArrivalTime();
        for(int i=0; i< list.size(); i++) {
            if (list.get(i).getArrivalTime()>clock){
                clock=list.get(i).getArrivalTime()+list.get(i).getComputationTime();
                list.get(i).setTA(clock -list.get(i).getArrivalTime());
                sum+=list.get(i).getTA();
            }
            else {
                clock += list.get(i).getComputationTime();
                list.get(i).setTA(clock - list.get(i).getArrivalTime());
                sum += list.get(i).getTA();
            }
        }
        double result=sum/list.size();
        System.out.println("FCFS: mean turnaround = "+result);
    }
}
