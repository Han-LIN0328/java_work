import java.util.Scanner;

public class TeachingWerewolfGame {
    static class Player {
        private int id;
        private String role;
        private boolean alive;
        // private id,role,alive
        public Player(){
            this.id=id;
            this.role=role;
            this.alive=true;
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
            alive=false;
        }
        public String getPublicInfo(){
            if(alive){
                return "Player " + id + "[Alive]";
            }else{
                return "Player " + id + "[Dead]";
            }s
        } public static void main(String[] args){
            Scanner sc =new Scanner(System.in);
            Random rand=new Random();
            System.out.println("wolfGame");
            System.out.println("Enter number of palyers(4-10):");
            int n=scnextInt();
            sc.nextLine();
            while (n<4||n>10) {
                System.out.println();
                n=sc.nextInt();
                sc.nextLine();
            }
            Player[] player=new Player[n];
            int wolfIndex=rand.Int();
            for (int i=0;i<n;i++){
                if(i==WolfIndex){
                    player[i]=new Player(i+1,role="Werewolf");
                }else{
                    player[i]=new Player(i+1,role="Villager");
                }
                System.out.println();
                System.out.print("ROle assignment start.");
                System.out print("Each player take turn to role.");
                for(int i=0,i<n;i++){
                    System.out.println();
                    System.out.ptinyln("Player",+(i+1)+"Please Enter.");
                    sc.nextLine();
                    System.out.println("Your Role: "+player[i].getRole());
                    System.out.print("Memorzie your role,then turn");
                    sc.nextLine();
                    for(int line=0;line<30;line++){
                        System.out.println();
                    }
                }
                boolean gameOver=false;
                int round=1;
                while(!gameOver){
                    System.out.println("Round",+ round);
                    System.out.println();

                    System.out.println("Night falls.Werewolf wakes up.");
                    int alvieWereWolf=findALiveWereWolf(players);
                       if (alvieWereWolf !=-1){
                            System.out.println("Werewolf is your turn.");
                            System.out.println("Alive players");
                            printAlivePlayers(players);
                            int target= -1;
                        while(true){
                            System.out.println("choose a player to kill");
                            if(sc.hasNext()){
                                targetId=sc.nextInt();
                                sc.nextLine();
                                if(isAliveTarget(targetId,player[alvieWereWolf].getId())){
                                    break;
                                }else{
                                    System.out.println("Invalid target.Please choose an alive player.");
                                    sc.nextLine();
                                }
                            }
                        }
                        players[targetId-1].kill();
                        System.out.println("Night results: Player"+targetid+"has been kill");
                        else{
                            System.out.println("No werewolf alive.Skipping night phase")
                        }
                        if(checkillvillagerwin(players)){
                            System.out.println("Villagers win! All wereloes have been killed.");
                            gameOver=true;
                        }else if(checkKillwerewolfwin(players)){
                            System.out.println();
                            System.out.println("Werewolf wins!All villagers have been killed.");
                            gameOver=ture;
                        }
                        if(gameOver){
                            break;
                        }
                        int voteId=-1;
                        public static int printAlivePlayers(player[] players){
                            for(int i=0;i<players.length;i++){
                                if(player[i].isAlive()&& player[i].getRole().equals("Werewolf")){
                                    return i;
                                }
                            }
                            return -1;
                        }
                         public static void printAlivePlayers(player[] players){
                            for(int i=0;i<players.length;i++){
                                System.out.prinln(player[i].gatPublicInfo());
                            }
                        }
                    }

                }

            }  
        } 
    }
                                

}