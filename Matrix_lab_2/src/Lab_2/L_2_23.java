package Lab_2;

import java.util.ArrayList;
import java.util.Collections;

public class L_2_23 {
    public static void main(String[] args) {
        int[][] matr = {{20, 100, 12, 11, 10, 100, 4},
                {32, 100, 13, 100, 9, 100, 3},
                {18, 100, 14, 100, 8, 100, 4},
                {17, 16, 15, 100, 7, 6, 5},
        };
        System.out.println(max_of_local_mins(matr));


    }

    public static boolean Is_local_min(int i, int j, int[][] my_matrix) {
        int tmp_value = my_matrix[i][j];
        if (tmp_value > my_matrix[i - 1][j]) {
            return false;
        }
        if (tmp_value > my_matrix[i][j - 1]) {
            return false;
        }
        if (tmp_value > my_matrix[i + 1][j]) {
            return false;
        }
        if (tmp_value > my_matrix[i][j + 1]) {
            return false;
        }

        return true;

    }

    public static int max_of_local_mins(int[][] my_matrix) {
        int rows = my_matrix.length;
        int columns = my_matrix[0].length;
        if (rows % 2 == 1) {
            rows = rows * 2 + 1;
        } else {
            rows *= 2;
        }
        if (columns % 2 == 1) {
            columns = columns * 2 + 1;
        } else {
            columns *= 2;
        }

        int[][] surrounding_matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if ( i == 0 || j == 0 || i == rows - 1 || j == columns - 1){
                    surrounding_matrix[i][j] = -2147483648;
                }
                else {
                    surrounding_matrix[i][j] = my_matrix[i - 1][j - 1];
                }
            }
        }

        ArrayList<Integer> locals = new ArrayList<Integer>();
        for (int i = 0; i < surrounding_matrix.length; i++) {
            for (int j = 0; j < surrounding_matrix[i].length; j++) {
                if (Is_local_min(i, j, my_matrix)) {
                    locals.add(my_matrix[i][j]);
                }
            }
        }
        Collections.sort(locals);
        return locals.get(locals.size() - 1);
    }

}

