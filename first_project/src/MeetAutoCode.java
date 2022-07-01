
import java.util.*;


public class MeetAutoCode {
    public static void main(String[] args){
        //System.out.println("Hello,Autocode!");
        int a = 4;
        int b = 8;
        swap(a , b);

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Hello, "+name);

        //print_all(merge_2(new int[]{5, 7, 22}, new int[]{ 2, 5}) );



    }




    public static boolean are_equal(double x1, double x2, double result){
        return Math.abs(x1 + x2 - result) <= 0.0001;
    }


    public static void swap(int a, int b){
        int tmp = a;
        a = b;
        b = tmp;

    }


//    public static void print_all(int... array){
//        for (int elem: array){
//            System.out.println(elem);
//        }
//    }

    public static void print_all(int[] array){
        for (int elem: array){
            System.out.println(elem);
        }
    }


    public static int[] merge_2(int[] first, int[] second){
        int[] result = new int[first.length + second.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (k != first.length + second.length - 1){
            if (first[i] <= second[j]){
                result[k] = first[i];
                k++;
                i++;
            }
            else {
                result[k] = second[j];
                j++;
                k++;
            }
        }
        return result;
    }
}
