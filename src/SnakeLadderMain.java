import model.Game;
import model.Player;

import java.util.Scanner;

public class SnakeLadderMain {

    public static void main(String args[]){

        Scanner scn = new Scanner(System.in);
        System.out.println("Please provide board size");
        int boardSeize = scn.nextInt();

        System.out.println("Please provide num of snakes in game");
        int numOfSake  = scn.nextInt();

        System.out.println("Please provide num of ladder in game");
        int numOfLadder = scn.nextInt();

        System.out.println("Please provide num of players");
        int numOfPlayer = scn.nextInt();

        Game game = new Game(numOfSake,numOfLadder,boardSeize);
        for(int i = 0; i < numOfPlayer; i++){
            System.out.println("Please enter Player " + i +" name");
            String name = scn.next();
            Player pl = new Player(name,0,false);
            game.addPlayer(pl);
        }

        game.startGame();

    }
}
