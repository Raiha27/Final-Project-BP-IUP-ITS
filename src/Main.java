import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to the Snakes and Ladders !");
        System.out.println();
        System.out.println("The objective of this game is to reach the 100th tile first !");
        System.out.println("But be careful ! If you step on a snake tile, you will fall down...");
        System.out.println("But also if you step on a ladder tile, you will go up !");
        System.out.println("The snake tiles are colored in " + Pallette.ANSI_RED+ "RED" + Pallette.ANSI_RESET+ " and ladder tiles in " + Pallette.ANSI_GREEN+"GREEN" + Pallette.ANSI_RESET);
        System.out.println("And the players will be displayed in " + Pallette.ANSI_CYAN + "BLUE." + Pallette.ANSI_RESET);
        System.out.println();
        System.out.println("Now, please enter the number of players (max 6).");

        int numPlayers = read.nextInt();
        while(numPlayers < 1 || numPlayers > 6)
        {
            System.out.println("The number of player should be 1~6 !");
            numPlayers = read.nextInt();
        }
        /*
        if(numPlayers > 6)
            throw new IllegalArgumentException("Maximum number of players is 6.");
        */

        Game game = new Game(numPlayers);

        while(game.getWinner() == null){

            for(int i = 0; i < numPlayers; i++) {

                game.setCurrentPlayer(game.getPlayers()[i]);
                Player player = game.getCurrentPlayer();

                System.out.println("* " + player.getName() + "'s turn ! *");
                System.out.println();
                System.out.println("GAME TABLE :");
                game.printTable();

                System.out.println("Please enter anything to roll the dice");

                String rolling = read.nextLine();

                while(rolling.isBlank())
                    rolling = read.nextLine();

                game.rollsDice();
                System.out.println(player.getName() + " has rolled " + game.getDice());
                int currentCase = player.getCurrentCase();
                player.move(game.getDice());
                System.out.println(player.getName() + " has moved from " + currentCase + " to " + player.getCurrentCase());
                System.out.println();
                System.out.println("GAME TABLE :");
                game.printTable();

                currentCase = player.getCurrentCase();

                int[] startCase = game.stepOnSpecialCase(currentCase);
                if(startCase[1] != -1){
                    int ladder_or_snake = startCase[0];
                    int finalCase;

                    if(ladder_or_snake == 0) {
                        finalCase = game.getFinalLadders()[startCase[1]];
                        System.out.println(player.getName() + " has found a ladder !");
                    }
                    else {
                        finalCase = game.getFinalSnakes()[startCase[1]];
                        System.out.println(player.getName() + " has been bitten by a snake !");
                    }

                    currentCase = player.getCurrentCase();
                    player.setCurrentCase(finalCase);

                    System.out.println(player.getName() + " has moved from " + currentCase + " to " + player.getCurrentCase());
                    System.out.println();
                    System.out.println("GAME TABLE :");
                    game.printTable();
                }

                if(player.getCurrentCase() == 100) {
                    player.won();
                    game.setWinner(player);
                    System.out.println("Congratulations ! " + player.getName() + " has won !");
                }

            }
        }



    }
}
