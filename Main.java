import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //lists out the matrix operations
        System.out.println("Matrix Operations:");
        System.out.println("[A] for Addition");
        System.out.println("[S] for Subtraction");
        System.out.println("[M] for Multiplication");
        System.out.println("[T] for Transpose");
        System.out.println("[D] for Determinant");
        
        //ask user what operation will they use
        System.out.print("What Matrix Operation will you use: ");
        String operation = input.nextLine();

        //get the input operation user even if its capitalized or not
        if (operation.equalsIgnoreCase("A") || operation.equalsIgnoreCase("S") || operation.equalsIgnoreCase("M")) {
            // Input for Matrix A
            System.out.println("Matrix A");
            System.out.print("Enter rows: ");
            int rowsA = input.nextInt();
            System.out.print("Enter columns: ");
            int colsA = input.nextInt();
            ArrayList<ArrayList<Integer>> matrixA = inputMatrix(input, rowsA, colsA, "A");

            // Input for Matrix B
            System.out.println("Matrix B");
            System.out.print("Enter rows: ");
            int rowsB = input.nextInt();
            System.out.print("Enter columns: ");
            int colsB = input.nextInt();
            ArrayList<ArrayList<Integer>> matrixB = inputMatrix(input, rowsB, colsB, "B");

            //addition

            if (operation.equalsIgnoreCase("A")) {
                if (rowsA != rowsB || colsA != colsB) {
                    System.out.println("Matrix sizes do not match for addition.");
                    return;
                }
                System.out.println("Result of A + B: ");
                displayMatrix(addMatrices(matrixA, matrixB));

                //subtraction

            } else if (operation.equalsIgnoreCase("S")) {
                if (rowsA != rowsB || colsA != colsB) {
                    System.out.println("Matrix sizes do not match for subtraction.");
                    return;
                }
                System.out.println("Result of A - B: ");
                displayMatrix(subtractMatrices(matrixA, matrixB));

                //multiplication

            } else if (operation.equalsIgnoreCase("M")) {
                if (colsA != rowsB) {
                    System.out.println("Matrix sizes are not compatible for multiplication.");
                    return;
                }
                System.out.println("Result of A x B: ");
                displayMatrix(multiplyMatrices(matrixA, matrixB));
            }

            //transpose

        } else if (operation.equalsIgnoreCase("T")) {
            System.out.print("Enter rows: ");
            int rows = input.nextInt();
            System.out.print("Enter columns: ");
            int cols = input.nextInt();
            ArrayList<ArrayList<Integer>> matrix = inputMatrix(input, rows, cols, "A");
            System.out.println("Transpose of Matrix A: ");
            displayMatrix(transposeMatrix(matrix));

            //determinants

        } else if (operation.equalsIgnoreCase("D")) {
            System.out.print("Enter size of square matrix (2 or 3): ");
            int n = input.nextInt();
            if (n != 2 && n != 3) {
                System.out.println("Only 2x2 or 3x3 matrices are supported.");
                return;
            }
            ArrayList<ArrayList<Integer>> matrix = inputMatrix(input, n, n, "A");
            int det = determinant(matrix);
            System.out.println("Determinant = " + det);
        } else {
            System.out.println("Invalid operation selected.");
        }
    }

    //matrix methods 

    public static ArrayList<ArrayList<Integer>> inputMatrix(Scanner input, int rows, int cols, String name) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                System.out.print(name + "[" + (i + 1) + "][" + (j + 1) + "]: ");
                row.add(input.nextInt());
            }
            matrix.add(row);
        }
        return matrix;
    }

    public static void displayMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (ArrayList<Integer> row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> addMatrices(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < a.get(0).size(); j++) {
                row.add(a.get(i).get(j) + b.get(i).get(j));
            }
            result.add(row);
        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> subtractMatrices(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < a.get(0).size(); j++) {
                row.add(a.get(i).get(j) - b.get(i).get(j));
            }
            result.add(row);
        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> multiplyMatrices(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
        int rowsA = a.size();
        int colsA = a.get(0).size();
        int colsB = b.get(0).size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < rowsA; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < colsB; j++) {
                int sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += a.get(i).get(k) * b.get(k).get(j);
                }
                row.add(sum);
            }
            result.add(row);
        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> transposeMatrix(ArrayList<ArrayList<Integer>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < cols; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                row.add(matrix.get(j).get(i));
            }
            result.add(row);
        }
        return result;
    }

    public static int determinant(ArrayList<ArrayList<Integer>> matrix) {
        int n = matrix.size();
        if (n == 2) {
            return matrix.get(0).get(0) * matrix.get(1).get(1) - matrix.get(0).get(1) * matrix.get(1).get(0);
        } else if (n == 3) {
            int a = matrix.get(0).get(0), b = matrix.get(0).get(1), c = matrix.get(0).get(2);
            int d = matrix.get(1).get(0), e = matrix.get(1).get(1), f = matrix.get(1).get(2);
            int g = matrix.get(2).get(0), h = matrix.get(2).get(1), i = matrix.get(2).get(2);
            return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
        }
        return 0;
    }
}
