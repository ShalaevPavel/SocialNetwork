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
        if (i > 0 && tmp_value > my_matrix[i - 1][j]) {
            return false;
        }
        if (j > 0 && tmp_value > my_matrix[i][j - 1]) {
            return false;
        }
        if (i < my_matrix.length - 1 && tmp_value > my_matrix[i + 1][j]) {
            return false;
        }
        if (j < my_matrix[0].length - 1 && tmp_value > my_matrix[i][j + 1]) {
            return false;
        }

        return true;

    }

    public static int max_of_local_mins(int[][] my_matrix) {
        ArrayList<Integer> locals = new ArrayList<Integer>();
        for (int i = 0; i < my_matrix.length; i++) {
            for (int j = 0; j < my_matrix[i].length; j++) {
                if (Is_local_min(i, j, my_matrix)) {
                    locals.add(my_matrix[i][j]);
                }
            }
        }
        Collections.sort(locals);
        return locals.get(locals.size() - 1);
    }

}

