import java.lang.Math;

public class Game {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
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

        for(int i = 1; i <= numPlayers; i++)
        {
            this.players[i-1] = new Player(i,0);
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
                    for (int value : n) System.out.print(ANSI_CYAN + "P" + value + ANSI_RESET);
                    System.out.print(" ");
                }
                else {
                    if(contains(this.startLadders,this.gameTable[i][j]) != -1)
                        System.out.print(ANSI_GREEN + this.gameTable[i][j] + ANSI_RESET);
                    else if(contains(this.startSnakes,this.gameTable[i][j]) != -1)
                        System.out.print(ANSI_RED + this.gameTable[i][j] + ANSI_RESET);
                    else {
                        System.out.print(this.gameTable[i][j]);
                    }
                    System.out.print(" ");
                }
                if(i == 9)
                    System.out.print(" ");
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
        this.dice = (int)(Math.random()*(6-1+1) + 1);
    }
    public int getDice(){
        return this.dice;
    }

}
