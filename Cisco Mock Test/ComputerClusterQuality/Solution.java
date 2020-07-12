import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'maximumClusterQuality' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY speed
     *  2. INTEGER_ARRAY reliability
     *  3. INTEGER maxMachines
     */

    public static long maximumClusterQuality(List<Integer> speed, List<Integer> reliability, int maxMachines) {
    // Write your code here
    int len = speed.size();

    int i = 0;
    List<Machines> machines = new ArrayList<>(len);
    
    Iterator<Integer> mspeed = speed.iterator();
    Iterator<Integer> mreliability = reliability.iterator();


    for(i = 0; i < len; i++)
    {
        machines.add(new Machines(mspeed.next(), mreliability.next()));
    }

    Collections.sort(machines, new Comparator<Machines>(){
        @Override
        public int compare(Machines m1, Machines m2) {
        if (m1.reliability > m2.reliability)
        return -1;
        if (m1.reliability < m2.reliability)
        return 1;

        return 0;
        }
    });

    long maxQuality = Integer.MIN_VALUE;
    int minReliability = Integer.MAX_VALUE;
    int rel = 0;
    int rspeed = 0;
    for(i = 0; i < maxMachines; i++)
    {
        Machines mac = machines.get(i);
        if(minReliability > mac.reliability)
        {
            minReliability = mac.reliability;
        }

        rspeed += mac.speed;

        if(maxQuality < (rspeed * minReliability))
        {
            maxQuality = (rspeed * minReliability);
        }
    }

    return maxQuality;

}

}

class Machines {
    int speed, reliability;

    public Machines(int speed, int reliability){
        this.speed = speed;
        this.reliability = reliability;
    }
}
 class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int speedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> speed = IntStream.range(0, speedCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int reliabilityCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> reliability = IntStream.range(0, reliabilityCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int maxMachines = Integer.parseInt(bufferedReader.readLine().trim());

        long result = Result.maximumClusterQuality(speed, reliability, maxMachines);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
