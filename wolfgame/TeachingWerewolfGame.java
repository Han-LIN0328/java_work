import java.util.Scanner;
import java.util.Random;

public class TeachingWerewolfGame {
    static class Player {
        private int id;
        private String role;
        private boolean alive;        
        // Constructor that accepts id and role
        public Player(int id, String role){
            this.id = id;
            this.role = role;
            this.alive = true;
        }

        public int getId(){
            return id;
        }

        public String getRole(){
            return role;
        }

        public boolean isAlive(){
            return alive;
        }

        public void kill(){
            alive = false;  
        }

        public String getPublicInfo(){
            if(alive){
                return "Player " + id + "[Alive]";
            }else{
                return "Player " + id + "[Dead]";
            }
        } 
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("wolfGame");
        System.out.println("Enter number of players (4-10):");
        
        int n = 0;
        if (sc.hasNextInt()) {
            n = sc.nextInt();
        } else {
            sc.nextLine(); // consume invalid input
        }
        sc.nextLine(); // consume newline
        
        while (n < 4 || n > 10) {
            System.out.println("Invalid number. Please enter 4-10:");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
            } else {
                sc.nextLine(); // consume invalid input if any
            }
            sc.nextLine();
        }

        Player[] players = new Player[n]; // Use 'players' consistently
        int wolfIndex = rand.nextInt(n);
        
        for (int i = 0; i < n; i++){
            if(i == wolfIndex){
                players[i] = new Player(i + 1, "Werewolf");
            }else{
                players[i] = new Player(i + 1, "Villager");
            }
        }

        System.out.println();
        System.out.print("Role assignment start.");
        System.out.print("Each player take turn to check role.");

        for(int i = 0; i < n; i++){
            System.out.println();
            System.out.println("Player " + (i + 1) + ", Please Enter.");
            sc.nextLine();
            System.out.println("Your Role: " + players[i].getRole());
            System.out.print("Memorize your role, then Enter.");
            sc.nextLine();
            for(int line = 0; line < 30; line++){
                System.out.println();
            }
        }

        boolean gameOver = false;
        int round = 1;
        
        while(!gameOver){
            System.out.println("Round " + round);
            System.out.println();

            System.out.println("Night falls. Werewolf wakes up.");
            int aliveWerewolfIndex = findAliveWerewolf(players);
            
            if (aliveWerewolfIndex != -1){
                System.out.println("Werewolf is your turn.");
                System.out.println("Alive players");
                printAlivePlayers(players);
                
                int targetId = -1;
                while(true){
                    System.out.println("choose a player to kill (ID)");
                    if(sc.hasNextInt()){
                        targetId = sc.nextInt();
                        sc.nextLine();
                        if(isAliveTarget(targetId, players, players[aliveWerewolfIndex].getId())){
                            break;
                        }else{
                            System.out.println("Invalid target. Please choose an alive player.");
                        }
                    } else {
                        sc.nextLine(); // consume non-int input
                    }
                }
                
                players[targetId - 1].kill();
                System.out.println("Night results: Player " + targetId + " has been killed");
            } else {
                System.out.println("No werewolf alive. Skipping night phase");
            }

            if(checkVillagerWin(players)){
                System.out.println("Villagers win! All werewolves have been killed.");
                gameOver = true;
            }else if(checkWerewolfWin(players)){
                System.out.println();
                System.out.println("Werewolf wins! All villagers have been killed.");
                gameOver = true;
            }
            
            if(gameOver){
                break;
            }
            round++;
        }
        sc.close();
    }
    
    // Helper methods moved outside main
    public static int findAliveWerewolf(Player[] players){
        for(int i = 0; i < players.length; i++){
            if(players[i].isAlive() && "Werewolf".equals(players[i].getRole())){
                return i;
            }
        }
        return -1;
    }

    public static void printAlivePlayers(Player[] players){
        for(int i = 0; i < players.length; i++){
            System.out.println(players[i].getPublicInfo());
        }
    }

    public static boolean isAliveTarget(int targetId, Player[] players, int selfId){
        if (targetId < 1 || targetId > players.length) return false;
        if (targetId == selfId) return false;
        return players[targetId - 1].isAlive();
    }

    public static boolean checkVillagerWin(Player[] players){
        for(Player p : players){
            if(p.isAlive() && "Werewolf".equals(p.getRole())){
                return false; 
            }
        }
        return true; 
    }

    public static boolean checkWerewolfWin(Player[] players){
         int werewolfCount = 0;
         int villagerCount = 0;
         for(Player p : players){
             if(p.isAlive()){
                 if("Werewolf".equals(p.getRole())){
                     werewolfCount++;
                 }else{
                     villagerCount++;
                 }
             }
         }
         return werewolfCount >= villagerCount;
    }
}

                                

