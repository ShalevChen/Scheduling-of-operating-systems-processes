
package test;
import java.util.ArrayList;
import java.util.Collections;

public class LCFSNP implements Timings{
    @Override
    public void solution(ArrayList<Process> originalList) {
        ArrayList<Process> list=new ArrayList<Process>(originalList.size());
        for(int i=0; i<originalList.size(); i++){
            Process p=originalList.get(i);
            Process newp=new Process(p.getArrivalTime(),p.getComputationTime(),p.getTA());
            list.add(newp);
        }
        Collections.sort(list,(p1, p2)->p1.getArrivalTime()-p2.getArrivalTime());
        int counter=0; int clock= list.get(0).getArrivalTime();int index=0;double sum=0;
        while(counter!=list.size()){
            if(clock>list.get(list.size()-1).getArrivalTime()){
                index = list.size() - 1;
                for(int i = index; i >= 0; i--){
                    if (!list.get(i).isDone()) {
                        clock += list.get(i).getComputationTime();
                        list.get(i).setTA(clock-list.get(i).getArrivalTime());
                        sum+=list.get(i).getTA();
                        list.get(i).setDone(true);
                        counter++;
                    }
                }
            }
            else if(list.get(index).getArrivalTime()<=clock)
                index++;
            else if(list.get(index-1).getArrivalTime()<=clock && !list.get(index-1).isDone()) {
                    clock+=list.get(index-1).getComputationTime();
                    list.get(index-1).setTA(clock -list.get(index-1).getArrivalTime());
                    list.get(index-1).setDone(true);
                    sum+=list.get(index-1).getTA();
                    counter++;
            }
            else if (list.get(index).getArrivalTime()>clock){
                clock=list.get(index).getArrivalTime()+list.get(index).getComputationTime();
                list.get(index).setTA(clock -list.get(index).getArrivalTime());
                list.get(index).setDone(true);
                sum+=list.get(index).getTA();
                counter++;
            }
        }System.out.println("LCFS NP: mean turnaround = "+sum/list.size());
    }
}
