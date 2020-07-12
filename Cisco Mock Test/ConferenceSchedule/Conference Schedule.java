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
     * Complete the 'maxPresentations' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY scheduleStart
     *  2. INTEGER_ARRAY scheduleEnd
     */

    public static int maxPresentations(List<Integer> scheduleStart, List<Integer> scheduleEnd) {
        int i = 0, count = 0;

        int len = scheduleStart.size();

        Iterator<Integer> start = scheduleStart.iterator();
        Iterator<Integer> end = scheduleEnd.iterator();

        List<Meetings> meetings = new ArrayList<>(len);


        for(i = 0; i < len; i++)
        {
            meetings.add(new Meetings(start.next(), end.next()));
        }

        Collections.sort(meetings, new Comparator<Meetings>(){
            @Override
            public int compare(Meetings m1, Meetings m2) {
            if (m1.end > m2.end)
            return 1;
            if (m1.end < m2.end)
            return -1;

            return 0;
            }
        });


        int j = 0;

        for(i = 1; i < scheduleEnd.size(); i++){
            if(meetings.get(j).end <= meetings.get(i).start){
                count++;
                j = i;
            }
        }

        count++;

        return count;
    }

}


/**
 * file
*/

class Meetings {
    int start, end;

    public Meetings(int start, int end)
    {
        this.start = start;
        this.end = end;
        
    }
}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int scheduleStartCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> scheduleStart = IntStream.range(0, scheduleStartCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int scheduleEndCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> scheduleEnd = IntStream.range(0, scheduleEndCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.maxPresentations(scheduleStart, scheduleEnd);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
