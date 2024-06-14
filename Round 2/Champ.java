import java.util.HashSet;
import java.util.Scanner;

class Champ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // System.out.print("Enter value of t: ");
        int t = sc.nextInt();
        for (int x = 0; x < t; x++) {
            // System.out.print("Enter value of n: ");
            int n = sc.nextInt();
            // System.out.print("Enter value of k: ");
            int k = sc.nextInt();
            // System.out.println("Value of N: " + n + " and K: " + k);
            int[] category = new int[n];
            int[] time = new int[n];
            // System.out.print("Enter values of category: ");
            for (int y = 0; y < n; y++) {
                category[y] = sc.nextInt();
            }
            // System.out.print("Enter values of time: ");
            for (int y = 0; y < n; y++) {
                time[y] = sc.nextInt();
            }
            sort(category, time);
            // System.out.println(Arrays.toString(category));
            // System.out.println(Arrays.toString(time));
            int result = helper(category, time, k);
            System.out.println("Minimum Time: " + result);
        }
        // close scanner;
        sc.close();
    }

    public static int helper(int category[], int time[], int k) {
        HashSet<Integer> myCategory = new HashSet<>();
        int result = 0;
        for (int x = 0; x < category.length; x++) {
            if (!myCategory.contains(category[x])) {
                result += time[x];
            }
            myCategory.add(category[x]);
            if (myCategory.size() == k) {
                return result;
            }
        }
        return -1;
    }

    public static void sort(int[] category, int[] time) {
        for (int x = 0; x < time.length - 1; x++) {
            for (int y = 0; y < time.length - 1; y++) {
                if (time[y] > time[y + 1]) {
                    int t1 = time[y];
                    time[y] = time[y + 1];
                    time[y + 1] = t1;
                    int t2 = category[y];
                    category[y] = category[y + 1];
                    category[y + 1] = t2;
                }
            }
        }
    }
}