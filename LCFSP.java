// יובל חגבי - 207797424
// חן שלו - 313584906
package test;
import java.util.ArrayList;
import java.util.Collections;

public class LCFSP implements Timings{
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
        int count=1, pre,i=0, flag=0;
        while(flag!=list.size()){
            pre=i;
            i++;
            if(i==list.size()){
                i%=list.size();
            }
            while(count<list.get(pre).getArrivalTime())
                count++;
            while(list.get(pre).getArrivalTime()==list.get(i).getArrivalTime()){
                pre=i;
                i++;
            }
            while(count!=list.get(i).getArrivalTime()){
                if(list.get(pre).getNeedTime()!=0){
                    count++;
                    list.get(pre).setTA(list.get(pre).getTA()+1);
                }
                if(list.get(pre).getNeedTime()==list.get(pre).getTA()){
                    flag++;
                    list.get(pre).setDone(true);
                    list.get(pre).setCompleteTime(count);
                    break;
                }
            }
            if(i==list.size()-1){
                while(count<list.get(i).getArrivalTime())
                    count++;
                while(flag!=list.size()){
                    while(list.get(i).getNeedTime()!=list.get(i).getTA() && !list.get(i).isDone()){
                        list.get(i).setTA(list.get(i).getTA()+1);
                        count++;
                    }
                    if(!list.get(i).isDone()){
                        flag++;
                        list.get(i).setCompleteTime(count);
                        list.get(i).setDone(true);
                    }i--;
                }
            }
        }
        for(Process p:list){
            sum+=p.getCompleteTime()-p.getArrivalTime();
        }System.out.println("LCFS P: mean turnaround = "+sum/list.size());
    }
}
