package Lab_2;

import java.util.Scanner;

public class L_2_Input {
    public static void main(String[] args) {
        for (int[] iiner: get_matrix_from_user()){
            for (var elem: iiner){
                System.out.println(elem);
            }
            System.out.println(" ");
        }
    }
    public static int[][] get_matrix_from_user(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number "
                + "of rows of the matrix");
        var rows = sc.nextInt();
        System.out.println("Enter the number "
                + "of columns of the matrix");
        var columns = sc.nextInt();
        int[][] result = new int[rows][columns];
        for (var i=0; i < rows; i++){
            for (var j=0; j < columns; j++){
                result[i][j] = sc.nextInt();
            }
        }
        return result;
    }
}
