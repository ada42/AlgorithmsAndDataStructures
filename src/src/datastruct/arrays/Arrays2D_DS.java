package datastruct.arrays;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Arrays2D_DS {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int sumArray[] = new int[16];
        int sumIndex=0;
        for(int i=1;i<=4;i++){
            for(int j=1;j<=4;j++){
                       sumArray[sumIndex++]= computeSum(arr,i,j);  
            }
        }
        return maxSum(sumArray);
    }

    private static int computeSum(int[][] arr, int i, int j){
        int sum = 0;
        sum+=arr[i-1][j-1]+arr[i-1][j]+arr[i-1][j+1]+arr[i][j]+arr[i+1][j-1]+arr[i+1][j]+arr[i+1][j+1];
        return sum;
    }

    private static int maxSum(int[] sum ){
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<sum.length;i++){
            if(maxSum < sum[i]){
                maxSum=sum[i];
            }
        }
        return maxSum;
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
