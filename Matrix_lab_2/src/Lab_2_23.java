import java.util.*;

/*
3)Выведите номера столбцов, все элементы, которых четны.
Для каждого столбца с  отрицательным  элементом  на главной диагонали
вывести его номер и сумму всех элементов этого столбца.

13)Найти максимальное из чисел, встречающихся в заданной матрице более одного раза.


23)Найти максимум среди всех локальных минимумов заданной матрицы.
Элемент матрицы называется локальным максимумом, если он строго больше всех имеющихся у него соседей.
 Соседями элемента ajj в матрице назовем элементы aki  с i-1ki+1, j-1lj+1,(k,l)(i,j).

*/

public class Lab_2_23 {
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
