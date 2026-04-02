//comment 1
import java.util.ArrayList;
import java.util.Collections;

public class hashExperiment{
    static final int N =1 << 20;
    statsic final int repetitions = 30;

    public static void main(String[] args){
        keyValue[]data= generateData();

        double[] loadFactors = {0.75,0.80,0.85,0.90,0.95};

        System.out.println("Average time in seconds");
        System.out.println("---------------------------------------------");
        System.out.println("Load Factor |Entries | Open hash | Chained hash");

        for(double alpha:loadFactors){
            int entries = (int)(alpha*1_000_000);
            int m = 1_000_003;

            double openTime = timeOpenHash(data,entries,m);
            double chainedTime = timeChainedHash(data,entries,m);

            System.out.printf("a=%.0f%% |%d | %.5f | %.5f\n",alpha * 100,entries,openTime,chainedTime);
        }
    }
    static keyValue[] generateData(){
            keyValue[] data=new KeyValue[N];

            ArrayList<Integer>keys=new ArrayList<>();

            for(int i=1,i<=N,i++){
            keys.add(i);
    }

            Collections.shuffle(keys);

            for(int i=0,i<N,i++){
            data[i] = new keyValue(String.valueOf(keys.get(i)),String.valueOf(i+1));
            }
            return data;
    }
    static double timeOpenHash(keyValue[] data,int entries,int m){
        long total=0;
        for(int r=0;r<repetitions;r++){
            openHash table = new openHash(m);

            for(int i=0;i<entries;i++){
                table.insert(data[i].key,data[i].value);
            }
                long start = System.currentTimeMillis();

                for(int i =0;i<entries;i++){
                table.lookup(data[i].key);
                }
                long end = System.currentTimeMillis();

                total +=(end-start);
            }
            return(total/(double)repetitions)/1000.0;
        }
        static double timeChainedHash(keyValue[] data,int entries, int m){
            long total=0;

            for(int r=0;r<repetitions;r++){
                chainedHash table = new chainedHash(m);
            

                for(int i=0;i<entries;i++){
                table.insert(data[i].key,data[i].value);
                }
                long start = System.currentTimeMillis();

                for(int i=0;i<entries;i++){
                table.lookup(data[i].key);
                }
                long end = System.currentTimeMillis();

                total +=(end-start);
            }
            return(total/(double)repetitions)/1000.0;
        }
    }
    
