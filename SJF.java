// יובל חגבי - 207797424
// חן שלו - 313584906
package test;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class SJF implements Timings{
    @Override
    public void solution(ArrayList<Process> originalList) {
        ArrayList<Process> list=new ArrayList<Process>(originalList.size());
        for(int i=0; i<originalList.size(); i++){
            Process p=originalList.get(i);
            Process newp=new Process(p.getArrivalTime(),p.getComputationTime(),p.getTA());
            list.add(newp);
        }
        Collections.sort(list,(p1, p2)->p1.getArrivalTime()-p2.getArrivalTime());
        double sum=0;
        int counter=0, ST=0;
        while(true){
            int min=99, size=list.size();
            if(counter==list.size())
                break;
            for(int i=0; i<list.size(); i++) {
                if ((list.get(i).getArrivalTime() <= ST) && (!list.get(i).isDone()) && (list.get(i).getNeedTime() < min)) {
                    min = list.get(i).getNeedTime();
                    size = i;
                }
            }
            if(size==list.size())
                ST++;
            else{
                if(list.get(size).getNeedTime()!=0){
                    list.get(size).setNeedTime(list.get(size).getNeedTime()-1);
                    ST++;
                }
                if(list.get(size).getNeedTime()==0){
                    list.get(size).setCompleteTime(ST);
                    list.get(size).setDone(true);
                    counter++;
                }
            }
        }
        for(Process p:list){
            sum+=p.getCompleteTime()-p.getArrivalTime();
        }
        System.out.println("SJF: mean turnaround = "+sum/list.size());
    }
}
