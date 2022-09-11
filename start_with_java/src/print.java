import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class print {
    public static void main(String[] args) {

        //Scanner input = new Scanner(System.in);

        //String name= input.nextLine();

//        Scanner get_number = new Scanner(System.in);
//        System.out.println(get_number.nextInt());

        //System.out.println("my number is");

        //for (int i = 0; i < name.length();i++){
        // System.out.println(name.charAt(i));
        //}
        int[] a = {1, 5, 4, 3};
        int size = 4;
        for (int number : a) {
            System.out.println(number * 2);
        }
        //print_array_numbers(a, size);

        Man pavel = new Man("pavel", 19);
        System.out.println(pavel.age);
        var s = "hello";
        System.out.println(s);
        print.show_on_console("hi");



    }


    public static int get_modul(int number) {
        if (number <= 0) {
            return number * 5;
        } else {
            return number;
        }
    }


    public static void print_array_numbers(int[] array, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }

    public static double calculate_z(double a, double b, double c) {
        return ((a - 3) * b / 2) + c;

    }


    public static void show_on_console(String message){
        System.out.println(message);
    }

//    public static List<Integer> find_common_digits(int first, int second) {
//        int counter = 0;
//        String result;
//        String first_s = String.valueOf(first);
//        String second_s = String.valueOf(second);
//        first_s += second_s;
//        for (char digit: first_s){
//            counter += first_s.charAt(i);
//        }
//
//    }

}


class Man{
    String name;
    int age;

    Man(String name_, int age_) {
        name = name_;
        age = age_;
    }
}