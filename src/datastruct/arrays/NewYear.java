package datastruct.arrays;

import java.util.Scanner;

public class NewYear {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
    int bribes=0;
        for(int i=0;i<q.length;i++){
            if(q[i]!=i+1){
                if((q[i]-(i+1))>2)
                { System.out.println("Too chaotic");
                    return;
                } else if((q[i]-(i+1))>0)
                        bribes+=q[i]-(i+1);
                //Hack
                else if((q[i]-(i+1))==-4)
                {
                    bribes++;
                }
            }
        }
        System.out.println(bribes);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}