import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class FindMaxInSeq {
    public static int max() {
        List<Integer> integers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();
        if (data == 0) {
            return data;
        }
        while (data != 0) {
            integers.add(data);
            Scanner tmp = new Scanner(System.in);
            data = tmp.nextInt();


        }


        return Collections.max(integers);
    }

    public static void main(String[] args) {


        // Get a result of your code

        System.out.println(max());
    }
}
