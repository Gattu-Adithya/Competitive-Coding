package CircularPrinter;

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
     * Complete the 'getTime' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static long getTime(String s) {
    // Write your code here
        char ch[] = s.toCharArray();

        int n = s.length();

        int i = 0;

        char pointer = 'A';
        
        int sum = 0;

        for(i = 0; i < n; i++)
        {
            if(pointer != ch[i])
            {

                int p = pointer - 'A' + 1;
                int check = ch[i] - 'A' + 1;
                int clock = Math.abs((int) (p - check));
                int anti_clock = 26 - Math.abs((int) (p - check));
                sum += Integer.min(clock, anti_clock);
                pointer = ch[i];
            }
        }
        return sum;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = bufferedReader.readLine();

        long result = Result.getTime(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
