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
    public static void main(String[] args) throws FileNotFoundException{
        int[][] matr = {
                {32, 4, 31, 4, 6},
                {2, 2, 2, 8, 6},
                {3, 8, 3, 6, 31},
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
        my_array.sort(Collections.reverseOrder());

        Map<Integer, Integer> count_info = new LinkedHashMap<Integer, Integer>(); // maintainces the adding order
        for (var number : my_array) {
            if (count_info.containsKey(number)) {
                count_info.put(number, count_info.get(number) + 1);
            } else {
                count_info.put(number, 1);
            }
        }
        for (var key : count_info.keySet()) {
            if (count_info.get(key) != 1) {
                return key;
            }
        }


        return -666666;
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
