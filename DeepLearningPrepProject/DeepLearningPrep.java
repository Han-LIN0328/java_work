public class DeepLearningPrep {
    public static void main(String[] args) {
        System.out.println("=== Assignment 3-1: Matrix Multiplication ===");
        // 定義兩個 3x3 矩陣 A 和 B
        double[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[][] B = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };
        
        // 執行矩陣乘法
        double[][] C = multiplyMatrix(A, B);
        
        // 輸出結果
        System.out.println("Matrix C (A * B):");
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.printf("%6.1f ", C[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n=== Assignment 3-2: Finite Difference Derivative ===");
        double x = Math.PI / 2; // 測試點 x = pi/2
        double h = 1e-5;        // 微小變數 h
        
        // 測試函數 f(x) = sin(x) 的導數
        // 理論上 d/dx sin(x) = cos(x), 在 x=pi/2 時, cos(pi/2) = 0
        double derivativeSin = centralDifferenceSin(x, h);
        double exactCos = Math.cos(x);
        
        System.out.printf("測試函數 f(x) = sin(x) 於 x = PI/2\n");
        System.out.printf("中心差分導數計算結果: %.6f\n", derivativeSin);
        System.out.printf("理論精確值 cos(PI/2): %.6f\n", exactCos);
        
        // 測試函數 f(x) = x^2 的導數
        // 理論上 d/dx x^2 = 2x, 在 x=2 時, 2(2) = 4
        double testX = 2.0;
        double derivativeX2 = centralDifferenceXSquare(testX, h);
        System.out.printf("\n測試函數 f(x) = x^2 於 x = 2.0\n");
        System.out.printf("中心差分導數計算結果: %.6f\n", derivativeX2);
        System.out.printf("理論精確值 (2x): %.6f\n", 2 * testX);
    }

    // Assignment 3-1: 矩陣乘法實作
    public static double[][] multiplyMatrix(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    // Assignment 3-2: f(x) = sin(x) 的中心差分導數
    public static double centralDifferenceSin(double x, double h) {
        // 公式: (f(x+h) - f(x-h)) / 2h
        return (Math.sin(x + h) - Math.sin(x - h)) / (2 * h);
    }
    
    // Assignment 3-2: f(x) = x^2 的中心差分導數
    public static double centralDifferenceXSquare(double x, double h) {
        // 公式: (f(x+h) - f(x-h)) / 2h
        double fxPlusH = Math.pow(x + h, 2);
        double fxMinusH = Math.pow(x - h, 2);
        return (fxPlusH - fxMinusH) / (2 * h);
    }
}