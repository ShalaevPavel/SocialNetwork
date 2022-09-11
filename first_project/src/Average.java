import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Average {
    public static int Avarege() {
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
        int result = 0;
        for (var number: integers){
            result += number;
        }

        return result / integers.size();
    }

    public static void main(String[] args) {


        // Get a result of your code

        System.out.println(Avarege());
    }
}
