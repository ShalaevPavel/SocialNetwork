import java.io.FileNotFoundException;
import java.io.FileReader;
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
    public static void main(String[] args) throws FileNotFoundException {
        int[][] matr = {
                {5, 1},
                {2, 3}
        };
        System.out.println(max_of_local_mins(get_matrix_form_file()));


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

    public static String max_of_local_mins(int[][] my_matrix) {
        ArrayList<Integer> locals = new ArrayList<Integer>();
        for (int i = 0; i < my_matrix.length; i++) {
            for (int j = 0; j < my_matrix[i].length; j++) {
                if (Is_local_min(i, j, my_matrix)) {
                    locals.add(my_matrix[i][j]);
                }
            }
        }
        Collections.sort(locals);
        int max = locals.get(locals.size() - 1);
        StringBuilder result = new StringBuilder(Integer.toString(max));
        result.append(" ");
        int counter = 0;
        int elem = my_matrix[0][0];
        for (var matr: my_matrix){
            for (var element: matr){
                if (element == elem){
                    counter++;
                }
            }
        }
        if (counter == my_matrix.length * my_matrix[0].length){
            return "all are equal";
        }
        for (int i = 0; i < my_matrix.length; i++) {
            for (int j = 0; j < my_matrix[i].length; j++) {
                if (Is_local_min(i, j, my_matrix) && max == my_matrix[i][j]) {
                    result.append(Integer.toString(i));
                    result.append("|");
                    result.append(Integer.toString(j));
                    result.append(" ");
                }
            }
        }
        return result.toString();
    }

//    public static boolean Is_local_min(int i, int j, int[][] my_matrix) {
//        int tmp_value = my_matrix[i][j];
//        if (tmp_value > my_matrix[i - 1][j]) {
//            return false;
//        }
//        if (tmp_value > my_matrix[i][j - 1]) {
//            return false;
//        }
//        if (tmp_value > my_matrix[i + 1][j]) {
//            return false;
//        }
//        if (tmp_value > my_matrix[i][j + 1]) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    public static String max_of_local_mins(int[][] my_matrix) {
//        int rows = my_matrix.length;
//        int columns = my_matrix[0].length;
//        if (rows % 2 == 1) {
//            rows = rows * 2 + 1;
//        } else {
//            rows *= 2;
//        }
//        if (columns % 2 == 1) {
//            columns = columns * 2 + 1;
//        } else {
//            columns *= 2;
//        }
//
//        int[][] surrounding_matrix = new int[rows][columns];
//        for (int i = 0; i < rows; i++){
//            for (int j = 0; j < columns; j++){
//                if ( i == 0 || j == 0 || i == rows - 1 || j == columns - 1){
//                    surrounding_matrix[i][j] = -2147483648;
//                }
//                else {
//                    surrounding_matrix[i][j] = my_matrix[i - 1][j - 1];
//                }
//            }
//        }
//
//        ArrayList<Integer> locals = new ArrayList<Integer>();
//        for (int i = 1; i < surrounding_matrix.length - 1; i++) {
//            for (int j = 1; j < surrounding_matrix[i].length - 1; j++) {
//                if (Is_local_min(i, j, surrounding_matrix)) {
//                    locals.add(surrounding_matrix[i][j]);
//                }
//            }
//        }
//        Collections.sort(locals);
//        int max = locals.get(locals.size() - 1);
//        StringBuilder final_ = new StringBuilder(Integer.toString(max));
//        final_.append(" ");
//
//        for (int i = 0; i < surrounding_matrix.length; i++) {
//            for (int j = 0; j < surrounding_matrix[i].length; j++) {
//                if (Is_local_min(i, j, surrounding_matrix) && surrounding_matrix[i][j] == max ) {
//                    final_.append(Integer.toString(i));
//                    final_.append("|");
//                    final_.append(Integer.toString(j));
//                    final_.append(" ");
//                }
//            }
//        }
//        return final_.toString();
//    }

    public static int[][] get_matrix_form_file() throws FileNotFoundException {
        FileReader fr = new FileReader("/home/e/IdeaProjects/Matrix_lab_2/src/get_MATRIX.txt");
        Scanner sc = new Scanner(fr);
        int n;
        int m;
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] matrix = new int[n][m];
        for (var i : matrix) {
            for (int j = 0; j < m; j++) {
                i[j] = sc.nextInt();
            }

        }
        return matrix;
    }

}
