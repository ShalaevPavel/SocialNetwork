import java.util.*;


public class PizzaSplit {
    public static void main(String[] args) {
        //Write a program, reading number of people and number of pieces per pizza and then
        //printing minimum number of pizzas to order to split all the pizzas equally and with no remainder
        Scanner sc = new Scanner(System.in);
        int number_of_people = sc.nextInt();
        int number_of_slices = sc.nextInt();
        int number_of_pizzas = 1;
        while (true) {
            if (number_of_slices * number_of_pizzas % number_of_people == 0) {
                break;
            }
            number_of_pizzas++;
        }
        System.out.println(number_of_pizzas);


    }
}
