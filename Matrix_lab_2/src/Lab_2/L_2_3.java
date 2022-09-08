package Lab_2;

import java.util.ArrayList;

public class L_2_3 {
    public static void main(String[] args) {
        int[][] matr = {
                {1, 4, 1, 4, 6},
                {2, 2, 2, 4, 6},
                {3, 8, 3, 6, 32},
                {1, 2, 3, 4, 6}
        };

        int[][] matr2 = {
                {-3, 4, 1, 4, 6},
                {2, 2, 2, 4, 6},
                {3, 8, -2, 6, 32},
                {1, 2, 3, -1, 6}
        };

        task_3(matr2);
    }

    public static ArrayList<Integer> even_columns(int[][] my_matrix) { //works
        ArrayList<Integer> columns_numbers = new ArrayList<Integer>();
        for (int i = 0; i < my_matrix[0].length; i++) {
            int counter = 0;

            for (int j = 0; j < my_matrix.length; j++) {
                if (my_matrix[j][i] % 2 == 0) {
                    counter++;
                }

            }
            if (counter == my_matrix.length) {
                columns_numbers.add(i);
            }
        }
        return columns_numbers;
    }

    public static ArrayList<String> number_and_sum(int[][] my_matrix){
        ArrayList<String> number_and_sum = new ArrayList<String>();
        int min_length = Math.min(my_matrix.length, my_matrix[0].length);
        for (int i =0; i < min_length; i++){
            if (my_matrix[i][i] < 0){
                int sum = 0;
                for (int j = 0; j < my_matrix.length;j++){
                    sum += my_matrix[j][i];
                }

                String tmp= Integer.toString(i);
                tmp += "/";
                tmp += Integer.toString(i);
                tmp += " - ";
                tmp += Integer.toString(sum);
                number_and_sum.add(tmp);
            }
        }
        return number_and_sum;

    }

    public static void task_3(int[][] my_matrix){
        for (var elem : even_columns(my_matrix)) {
            System.out.println(elem);
        }

        for (var el: number_and_sum(my_matrix)){
            System.out.println(el);
        }

    }
}
