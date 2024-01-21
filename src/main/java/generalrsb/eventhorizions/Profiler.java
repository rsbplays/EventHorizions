package generalrsb.eventhorizions;

import java.util.ArrayList;
import java.util.HashMap;

public class Profiler {
    HashMap<String,timing> counts = new HashMap<String,timing>();

    long time;

    public Profiler(){
        time = System.nanoTime();
    }

    public void Start() {
        time = System.nanoTime();
    }

    public void profilerPush(String name){
        if (!counts.containsKey(name)){
            counts.put(name,new timing(System.nanoTime()-time,1));
        }else{
            counts.get(name).count +=1;
            counts.get(name).time+=System.nanoTime()-time;
        }
        time = System.nanoTime();
    }
    public void read(){
        long total = 0;
        for (String i: counts.keySet()){
            System.out.println(((counts.get(i).time/counts.get(i).count)/1000000D)+" : "+i);
            total+=counts.get(i).time/counts.get(i).count;

        }
        System.out.println("Total Time Taken: "+total/1000000D);

    }
    class timing{
        public long time;
        public int count;

        public timing(long time, int count){
            this.time = time;
            this.count = count;
        }
    }
}
