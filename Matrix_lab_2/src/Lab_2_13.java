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


public class Lab_2_13 {
    public static void main(String[] args) throws FileNotFoundException {
        int[][] matr = {
                {1, 2, 3},
                {4, 2, 3}
        };
        System.out.println(max_repeated(matr));

    }

    public static int max_repeated(int[][] my_matrix) {
        HashMap<Integer, Integer> count_info = new HashMap<Integer, Integer>();

        for (int[] myMatrix : my_matrix) {
            for (int key : myMatrix) {
                if (count_info.containsKey(key)) {
                    count_info.put(key, count_info.get(key) + 1);
                } else {
                    count_info.put(key, 1);
                }
            }
        }

        int max = -2147483648;


        for (var key : count_info.keySet()) {
            if (count_info.get(key) != 1 && key > max) {
                max = key;
            }
        }


        return max;
    }


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
