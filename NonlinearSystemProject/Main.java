import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 定義非線性系統的參數
        // x 代表目前的人口比例 (0.0 到 1.0 之間)
        // r 代表增長率
        double x = 0.5; 
        double r = 3.7; // 當 r > 3.57 時，系統會進入混沌狀態
        int iterations = 10; // 迭代次數

        System.out.println("--- 非線性系統模擬：羅吉斯映射 ---");
        System.out.println("初始值 x: " + x);
        System.out.println("增長率 r: " + r);
        System.out.println("--------------------------------");

        // 執行非線性迭代
        for (int i = 1; i <= iterations; i++) {
            // 非線性核心公式：x_next = r * x * (1 - x)
            x = r * x * (1 - x);
            
            // 輸出每一次迭代的結果
            System.out.printf("第 %d 次迭代: x = %.5f%n", i, x);
        }

        System.out.println("--------------------------------");
        System.out.println("觀察：小小的改變 r 或 x，結果會大幅震盪。");
    }
}