package Lab_2;

import Lab_2.L_2_Input;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class L_2_13 {
    public static void main(String[] args) {
        //int[][] mat =  L_2_Input.get_matrix_from_user();

        int[][] matr = {
                {32, 4, 1, 4, 6},
                {2, 2, 2, 8, 6},
                {3, 8, 3, 6, 32},
                {1, 2, 3, 4, 6}
        };
        System.out.println(max_repeated(matr));

    }

    public static int max_repeated(int[][] my_matrix) {
        ArrayList<Integer> my_array = new ArrayList<Integer>();
        for (int[] myMatrix : my_matrix) {
            for (int matrix : myMatrix) {
                my_array.add(matrix);
            }
        }
        Collections.sort(my_array);
        ArrayList<Integer> new_array = new ArrayList<Integer>();
        for (var i = 0; i < my_array.size(); i++) {
            var count = 0;
            for (Integer integer : my_array) {
                if (Objects.equals(my_array.get(i), integer)) {
                    count++;
                }
            }
            if (count >= 2) {

                new_array.add(my_array.get(i));
            }
        }

        return new_array.get(new_array.size() - 1);


    }

}


