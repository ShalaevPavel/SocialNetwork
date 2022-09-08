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
    public static void main(String[] args) {
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
