
import java.util.Locale;
import java.util.Scanner;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double disc = b * b - 4 * a * c;
        if (disc < 0){
            System.out.println("no roots");
        }
        else if (disc == 0){
            double tmp = (-b  + Math.sqrt(disc)) / 2 * a;
            System.out.println(tmp);
        }
        else {
           double r1 = (-b  + Math.sqrt(disc)) / 2 *a ;
           double r2 = (-b  - Math.sqrt(disc)) / 2* a;
           System.out.println(r1+" "+r2);
        }


    }

}