import java.lang.Math;

public class Game {
    private final int[][] gameTable = new int[10][10];
    private final int[] startLadders = {2,8,20,32,41,74,82,85};
    private final int[] finalLadders = {23,34,77,68,79,88,100,95};
    private final int[] startSnakes = {29,38,47,53,62,86,92,97};
    private final int[] finalSnakes = {9,15,5,33,37,54,70,25};
    private final int numPlayers;

    private final Player[] players;

    private Player currentPlayer;
    private int dice = 0;
    private Player winner = null;

    public Game(int numPlayers){
        this.numPlayers = numPlayers;
        int n = 100;
        for(int i = 0; i < 10; i++) 
        {
            for(int j = 0; j < 10; j++)
            {
                if(i % 2 == 0)
                    this.gameTable[i][j] = n;
                else
                    this.gameTable[i][9-j] = n;
                n -= 1;
            }
        }
        this.players = new Player[numPlayers];

        if(numPlayers > 1) {
            System.out.println();
            System.out.println("Each player will roll the dice to decide the players' turn.");
            int[] orders = new int[numPlayers];
            int[] exc = new int[6];

            for (int i = 0; i < numPlayers; i++) {
                int x = randomInt(exc);
                exc[i] = x;
                orders[i] = x;
                System.out.println("Player " + (i + 1) + " has rolled " + x);
            }
            System.out.println();

            for (int i = 0; i < numPlayers; i++) {
                int ind = maxList(orders);
                orders[ind] = -1;

                this.players[i] = new Player(ind+1, 0);
            }
            System.out.println("The order of players' turn will be as following:");
            for (int i = 0; i < numPlayers; i++) {
                System.out.println(players[i].getName());
            }
            System.out.println();
        }
        else {
            for (int i = 0; i < numPlayers; i++) {
                this.players[i] = new Player(i+1, 0);
            }
        }
    }
    private int[] isOccupied(int n){

        int count = 0;
        for(Player p : this.players)
        {
            if(p.getCurrentCase() == n)
                count++;
        }
        int[] res = new int[count];
        count = 0;
        for(Player p : this.players)
        {
            if(p.getCurrentCase() == n) {
                res[count] = p.getNumber();
                count++;
            }
        }
        return res;
    }
    public void printTable(){
        for(int i = 0; i < 10; i++)
        {
            //System.out.println(Arrays.toString(this.gameTable[i]));
            for(int j = 0; j < 10; j++){
                int[] n = isOccupied(this.gameTable[i][j]);
                if(n.length != 0){
                    for (int value : n) System.out.print(Pallette.ANSI_CYAN + "P" + value + Pallette.ANSI_RESET);
                    System.out.print("\t");
                }
                else {
                    if(contains(this.startLadders,this.gameTable[i][j]) != -1)
                        System.out.print(Pallette.ANSI_GREEN + this.gameTable[i][j] + Pallette.ANSI_RESET);
                    else if(contains(this.startSnakes,this.gameTable[i][j]) != -1)
                        System.out.print(Pallette.ANSI_RED + this.gameTable[i][j] + Pallette.ANSI_RESET);
                    else {
                        System.out.print(this.gameTable[i][j]);
                    }
                    System.out.print("\t");
                }
                /*
                if(i == 9)
                    System.out.print("\t");

                 */
            }
            System.out.println();
        }
        System.out.println();
    }
    private int contains(final int[] array, final int key) {
        int count = 0;
        for (final int i : array) {
            if (i == key) {
                return count;
            }
            count++;
        }
        return -1;
    }
    public int[] stepOnSpecialCase(int n){

        int[] res = new int[2];

        int a = contains(this.startLadders,n);
        if(a == -1) {
            a = contains(this.startSnakes, n);
            res[0] = 1;
        }
        res[1] = a;
        return res;
    }
    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    public int[] getStartLadders(){
        return this.startLadders;
    }
    public int[] getFinalLadders(){
        return this.finalLadders;
    }
    public int[] getStartLSnakes(){
        return this.startSnakes;
    }
    public int[] getFinalSnakes(){
        return this.finalSnakes;
    }
    public int[][] getGameTable(){
        return this.gameTable;
    }
    public Player[] getPlayers(){
        return this.players;
    }
    public Player getWinner(){
        return this.winner;
    }
    public void setWinner(Player player){
        this.winner = player;
    }
    public void rollsDice(){
        //this.dice = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        this.dice = (int)(Math.random()*6 + 1);
    }
    public int getDice(){
        return this.dice;
    }
    private int maxList(int[] L){
        int n = L.length;
        int max = L[0];
        int index = 0;
        for(int i = 1; i < n; i++)
        {
            if(L[i] > max) {
                max = L[i];
                index = i;
            }
        }
        return index;
    }
    private int randomInt(int[] exc){
        int res = (int)(Math.random()*6+1);
        while(contains(exc,res) != -1){
            res = (int)(Math.random()*6+1);
        }
        return res;
    }
}