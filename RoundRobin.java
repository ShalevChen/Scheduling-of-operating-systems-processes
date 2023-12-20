
package test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class RoundRobin implements Timings{
    @Override
    public void solution(ArrayList<Process> originalList) {
        ArrayList<Process> list=new ArrayList<Process>(originalList.size());
        for(int i=0; i<originalList.size(); i++){
            Process p=originalList.get(i);
            Process newp=new Process(p.getArrivalTime(),p.getComputationTime(),p.getTA());
            list.add(newp);
        }
        Collections.sort(list,(p1, p2)->p1.getArrivalTime()-p2.getArrivalTime());
        Process[] arr=new Process[list.size()];
        int clock=list.get(0).getArrivalTime(), numP=1, i=-1, index=1, counter=0, timeQ=2;
        double  sum=0;
        boolean flag=false;
        Integer inf = Integer.MAX_VALUE;
        i=(i+1)%numP;
        arr[i]=list.get(0);
        for(int j=0; j<inf; j++){
            if(counter==list.size())
                break;
            if(!arr[i].isDone()) {
                if(arr[i].getComputationTime()==0){
                    arr[i].setTA(0);
                    arr[i].setDone(true);
                    flag=true; counter++;
                }
                else if (arr[i].getTA() + timeQ < arr[i].getComputationTime()) {
                    clock += timeQ;
                    arr[i].setTA(list.get(i).getTA() + timeQ);
                }
                else if (arr[i].getTA() + timeQ == arr[i].getComputationTime()) {
                    clock += timeQ;
                    arr[i].setTA(clock - arr[i].getArrivalTime());
                    sum += arr[i].getTA();
                    arr[i].setDone(true);
                    counter++; flag=true;
                }
                else if (arr[i].getTA() + timeQ > arr[i].getComputationTime()) {
                    clock += ((arr[i].getTA()+timeQ)-arr[i].getComputationTime());
                    arr[i].setTA(clock - arr[i].getArrivalTime());
                    sum += arr[i].getTA();
                    arr[i].setDone(true);
                    counter++; flag=true;
                }
            }
            if(index<list.size()&& list.get(index).getArrivalTime()<=clock){
               arr[index]=(list.get(index));
                numP++;
                index++;
            }
            if(flag==true && index<list.size()&& list.get(index).getArrivalTime()>clock){
                arr[index]=(list.get(index));
                clock=arr[index].getArrivalTime();
                numP++;
                index++;
            }
            i=(i+1)%numP;
        }
        System.out.println("RoundRobin: mean turnaround = "+sum/list.size());
    }
}
