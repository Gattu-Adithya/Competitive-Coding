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
     * Complete the 'getMinDeletions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int getMinDeletions(String s) {
    // Write your code here
    HashMap<Character, Integer> map = new HashMap<>();


    for(char ch : s.toCharArray())
    {
        if(map.containsKey(ch))
        {
            int value = map.get(ch);
            value++;
            map.put(ch, value);
        }
        else{
            map.put(ch, 1);
        }
    }

    int minDel = 0;

    for(Character ch : map.keySet())
    {
        int value = map.get(ch);

        if(value > 1)
        {
            minDel += (value - 1);

        }
        // else{
        //     minDel += value;
        // }
    }

    return minDel;

    }

}

class Solution1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));


        String s = bufferedReader.readLine();

        int result = Result.getMinDeletions(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
