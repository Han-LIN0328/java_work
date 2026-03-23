import java.util.Scanner;

// 1. 玩家類別：練習類別與物件、封裝
class Player {
    private int id;
    private String role;   // 角色：Werewolf, Seer, Villager
    private String camp;   // 陣營：Good, Bad
    private boolean isAlive;

    // 建構子
    public Player(int id, String role, String camp) {
        this.id = id;
        this.role = role;
        this.camp = camp;
        this.isAlive = true; // 預設為存活
    }

    // Getters & Setters (封裝)
    public int getId() { return id; }
    public String getRole() { return role; }
    public String getCamp() { return camp; }
    public boolean isAlive() { return isAlive; }
    
    public void setAlive(boolean alive) { this.isAlive = alive; }
}

// 注意：這裡的類別名稱已經改成與檔名相同的 WerewolfGame0310
public class WerewolfGame0310 {
    // 2. 使用標準陣列 (Arrays) 儲存玩家
    private Player[] players;
    private Scanner scanner;

    // 建構子名稱也必須跟著改成 WerewolfGame0310
    public WerewolfGame0310() {
        scanner = new Scanner(System.in);
    }

    // 初始化遊戲與角色分配
    public void setup() {
        System.out.println("--- 遊戲初始化 ---");
        players = new Player[4]; // 建立長度為 4 的玩家陣列
        
        // 分配不同的角色與陣營
        players[0] = new Player(1, "Werewolf", "Bad");
        players[1] = new Player(2, "Seer", "Good");
        players[2] = new Player(3, "Villager", "Good");
        players[3] = new Player(4, "Villager", "Good");
        
        System.out.println("成功建立 4 名玩家 (1 狼人, 1 預言家, 2 平民)。");
    }

    // 3. 夜晚行動 (Night abilities)
    public void nightPhase() {
        System.out.println("\n--- 天黑請閉眼 ---");
        
        // 使用 for 迴圈輪詢玩家
        for (int i = 0; i < players.length; i++) {
            if (players[i].isAlive()) {
                // 條件判斷 (if / else if)
                if (players[i].getRole().equals("Werewolf")) {
                    System.out.print("狼人 (Player " + players[i].getId() + ")，請輸入你要擊殺的玩家 ID (1-4): ");
                    int targetId = scanner.nextInt(); // Scanner 輸入
                    killPlayer(targetId);
                } else if (players[i].getRole().equals("Seer")) {
                    System.out.print("預言家 (Player " + players[i].getId() + ")，請輸入你要查驗的玩家 ID (1-4): ");
                    int targetId = scanner.nextInt();
                    checkPlayer(targetId);
                }
            }
        }
    }

    // 4. 白天投票系統 (Day voting system)
    public void dayPhase() {
        System.out.println("\n--- 天亮請睜眼 ---");
        System.out.print("大家請討論。請輸入得票最高要被放逐的玩家 ID (1-4)，若無人出局請輸入 0: ");
        int targetId = scanner.nextInt();
        
        if (targetId != 0) {
            killPlayer(targetId);
            System.out.println("玩家 " + targetId + " 被投票出局！");
        } else {
            System.out.println("今天是平安日，無人出局。");
        }
    }

    // 方法 (Methods)：處理擊殺邏輯
    private void killPlayer(int targetId) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getId() == targetId) {
                players[i].setAlive(false); // 透過 setter 改變狀態
            }
        }
    }

    // 方法 (Methods)：處理查驗邏輯
    private void checkPlayer(int targetId) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getId() == targetId) {
                System.out.println("系統提示：玩家 " + targetId + " 的陣營是 " + players[i].getCamp());
            }
        }
    }

    // 5. 勝利條件偵測 (Win-condition detection)
    public boolean checkWin() {
        int goodCount = 0;
        int badCount = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i].isAlive()) {
                if (players[i].getCamp().equals("Good")) {
                    goodCount++;
                } else if (players[i].getCamp().equals("Bad")) {
                    badCount++;
                }
            }
        }

        if (badCount == 0) {
            System.out.println("\n遊戲結束：好人陣營獲勝！");
            return true;
        } else if (badCount >= goodCount) {
            System.out.println("\n遊戲結束：狼人陣營獲勝！");
            return true;
        }
        return false;
    }

    // 主程式入口
    public static void main(String[] args) {
        // 實例化物件的型別也更新為 WerewolfGame0310
        WerewolfGame0310 game = new WerewolfGame0310();
        game.setup();

        boolean gameOver = false;
        
        // 遊戲主迴圈 (while loop)
        while (!gameOver) {
            game.nightPhase(); // 執行夜晚
            gameOver = game.checkWin(); // 檢查是否結束
            if (gameOver) break;

            game.dayPhase(); // 執行白天
            gameOver = game.checkWin(); // 檢查是否結束
        }
        
        game.scanner.close();
    }
}