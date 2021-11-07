import java.util.*;

public class Sparse {

    public static int[][] toSparse (int[][] array) {

        int notZero = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0) {
                    notZero++;
                }
            }
        }
        int[][] sparse = new int[notZero][3];
        int terms = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] != 0) {
                    sparse[terms][0] = i;
                    sparse[terms][1] = j;
                    sparse[terms][2] = array[i][j];
                    terms++;
                }
            }
        }
        return sparse;
    }

    public static int[][] toNormal (int[][] array) {

        int row = 0,col = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] > row) {
                row = array[i][0];
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i][1] > col) {
                col = array[i][1];
            }
        }
        row++;col++;

        int[][] result = new int[row][col];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = search(i,j,array);
            }
        }
        return result;
    }

    public static void draw (int[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static int[][] add (int[][] array1, int[][] array2) {

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> repetitious = new ArrayList<>();

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i][0] == array2[j][0] && array1[i][1] == array2[j][1]) {
                    repetitious.add(array1[i][0]);
                    repetitious.add(array1[i][1]);
                    repetitious.add(array1[i][2] + array2[j][2]);
                } if (i == 0) {
                    list.add(array2[j][0]);
                    list.add(array2[j][1]);
                    list.add(array2[j][2]);
                }
            }
            list.add(array1[i][0]);
            list.add(array1[i][1]);
            list.add(array1[i][2]);
        }

        ArrayList<Integer> unique = new ArrayList<>(repetitious);
        ArrayList<Integer> toDelete = new ArrayList<>();

        for (int i = 0; i < list.size(); i+=3) {
            for (int j = 0; j < repetitious.size(); j+=3) {
                if (list.get(i) == repetitious.get(j) && list.get(i + 1) == repetitious.get(j + 1)) {
                    toDelete.add(i);
                }
            }
        }
        for (int i = 0; i < list.size(); i+=3) {
            if (!toDelete.contains(i)) {
                unique.add(list.get(i));
                unique.add(list.get(i + 1));
                unique.add(list.get(i + 2));
            }
        }

        int c = 0;
        int[][] array = new int[unique.size()/3][3];
        for (int i = 0; i < unique.size()/3; i++) {
            array[i][0] = unique.get(c++);
            array[i][1] = unique.get(c++);
            array[i][2] = unique.get(c++);
        }

        Arrays.sort(array, (a,b)-> {
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        Arrays.sort(array, (a,b)-> {
            if(a[0] == b[0]){
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        return array;
    }

    public static int[][] subtract (int[][] array1, int[][] array2) {

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> repetitious = new ArrayList<>();

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i][0] == array2[j][0] && array1[i][1] == array2[j][1]) {
                    repetitious.add(array1[i][0]);
                    repetitious.add(array1[i][1]);
                    repetitious.add(array1[i][2] - array2[j][2]);
                } if (i == 0) {
                    list.add(array2[j][0]);
                    list.add(array2[j][1]);
                    list.add(-1 * array2[j][2]);
                }
            }
            list.add(array1[i][0]);
            list.add(array1[i][1]);
            list.add(array1[i][2]);
        }

        ArrayList<Integer> unique = new ArrayList<>(repetitious);
        ArrayList<Integer> toDelete = new ArrayList<>();

        for (int i = 0; i < list.size(); i+=3) {
            for (int j = 0; j < repetitious.size(); j+=3) {
                if (list.get(i) == repetitious.get(j) && list.get(i + 1) == repetitious.get(j + 1)) {
                    toDelete.add(i);
                }
            }
        }
        for (int i = 0; i < list.size(); i+=3) {
            if (!toDelete.contains(i)) {
                unique.add(list.get(i));
                unique.add(list.get(i + 1));
                unique.add(list.get(i + 2));
            }
        }

        int c = 0;
        int[][] array = new int[unique.size()/3][3];
        for (int i = 0; i < unique.size()/3; i++) {
            array[i][0] = unique.get(c++);
            array[i][1] = unique.get(c++);
            array[i][2] = unique.get(c++);
        }

        Arrays.sort(array, (a,b)-> {
            if(a[1] == b[1]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        Arrays.sort(array, (a,b)-> {
            if(a[0] == b[0]){
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        return array;
    }

    public static int[][] transpose (int[][] array) {

        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][1] > max) {
                max = array[i][1];
            }
        }
        max++;

        int[][] result = new int[array.length][3];
        if (result.length > 0) {
            int[] rowSize = new int[max];
            for (int i = 0; i < max; i++) {
                rowSize[i] = 0;
            }
            for (int i = 0; i < array.length; i++) {
                rowSize[array[i][1]]++;
            }
            int[] startOfRow = new int[max];
            startOfRow[0] = 0;
            for (int i = 1; i < max; i++) {
                startOfRow[i] = startOfRow[i - 1] + rowSize[i - 1];
            }
            for (int i = 0; i < array.length; i++) {
                int x = startOfRow[array[i][1]]++;
                result[x][0] = array[i][1];
                result[x][1] = array[i][0];
                result[x][2] = array[i][2];
            }
        }
        return result;
    }

    public static int[][] multiply (int[][] array1, int[][] array2) {
        int row = 0,col = 0,c = 0;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i][0] > row) {
                row = array1[i][0];
            }
        }
        for (int i = 0; i < array2.length; i++) {
            if (array2[i][1] > col) {
                col = array2[i][1];
            }
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i][1] > c) {
                c = array1[i][1];
            }
        }
        c++;row++;col++;
        int[][] matrix = new int[row][col];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                for (int k = 0; k < c; k++) {
                    matrix[i][j] += (search(i,k,array1) * search(k,j,array2));
                }
            }
        }
        return toSparse(matrix);
    }

    public static int search (int x,int y,int[][] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i][0] == x && array[i][1] == y) {
                return array[i][2];
            }
        }
        return 0;
    }
}
