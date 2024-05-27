import java.util.Arrays;
public class Player {
    private final int number;
    private String name = "Player ";
    private int currentCase;
    private boolean win = false;
    public Player( int number, int currentCase){
        this.number = number;
        this.name += number;
        this.currentCase = currentCase;
    }
    public int getNumber(){
        return this.number;
    }
    public String getName(){
        return this.name;
    }
    public int getCurrentCase(){
        return this.currentCase;
    }
    public void setCurrentCase(int n){
        this.currentCase = n;
    }


//    public int[] getIndex(Game game){
//        int n = this.currentCase;
//        int[] res = new int[2];
//        res[0] = (100 - n)/10;
//        res[1] = Arrays.asList(game.getGameTable()[res[0]]).indexOf(this.currentCase);
//
//        return res;
//    }
    public void move(int n){
        if(this.currentCase + n > 100)
            this.currentCase = 100 - (this.currentCase + n - 100);
        else
            this.currentCase += n;
    }
    public void won(){
        this.win = true;
    }
    public boolean getWin(){
        return this.win;
    }


}
