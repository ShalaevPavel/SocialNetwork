import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloStrangers {
    public static void main(String[] args) throws IOException {
        //Write a program, asks for a number - amount of strangers to meet.
        //Then reads stranger names line by line and prints line by line "Hello, ...".
        Scanner scanner = new Scanner(System.in);
        int n_of_names = scanner.nextInt();
        if (n_of_names == 0) {
            System.out.println("Oh, it looks like there is no one here");
        } else if (n_of_names < 0) {
            System.out.println("Seriously? Why so negative?");
        } else {
            List<String> names = new ArrayList<>();
            for (int i = 0; i < n_of_names; i++) {
                Scanner tmp = new Scanner(System.in);
                String name = tmp.nextLine();
                names.add(name);
            }
            for (var Elem : names) {
                System.out.println(Elem);
            }
        }
    }
}


