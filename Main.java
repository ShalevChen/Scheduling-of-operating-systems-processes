// יובל חגבי - 207797424
// חן שלו - 313584906
package test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
public class Main {
    public static ArrayList<Process> createProcess(String name){
        int numOfProcess;
        ArrayList<Process> list=null;
        Scanner scanner=null;
        try {
            scanner=new Scanner(new BufferedReader(new FileReader(name)));
            numOfProcess=Integer.parseInt((scanner.next()));
            list= new ArrayList<Process>(numOfProcess);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(scanner.hasNext()){
            String[] temp=scanner.next().split(",");
            list.add(new Process(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),0));
        }scanner.close();
        return list;
    }
    public static void main(String[] args){
        for(int i=1; i<=5; i++) {
            ArrayList<Process> Original = new ArrayList<Process>();
            switch (i){
                case 1: {Original = createProcess("input1.txt");break;}
                case 2: {Original = createProcess("input2.txt");break;}
                case 3: {Original = createProcess("input3.txt");break;}
                case 4: {Original = createProcess("input4.txt");break;}
                case 5: {Original = createProcess("input5.txt");break;}
            }
            System.out.println("File: "+i);
            Timings FcFs = new FCFS();
            FcFs.solution(Original);
            Timings LcFsNP = new LCFSNP();
            LcFsNP.solution(Original);
            Timings LcFsP = new LCFSP();
            LcFsP.solution(Original);
            Timings rr = new RoundRobin();
            rr.solution(Original);
            Timings sjf = new SJF();
            sjf.solution(Original);
            System.out.println("-----------------------------------------");
        }
    }
}