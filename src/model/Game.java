package model;

import java.util.*;

public class Game {

    int numOfSnakes = 0;
    int numOfLadder = 0;

    List<Snake> snakeList;
    List<Ladder> ladderList;

    Queue<Player> playerQueue;

    Board board;
    Dice dice;

    public Game(int numOfSnakes, int numOfLadder, int boardSize) {
        this.numOfSnakes = numOfSnakes;
        this.numOfLadder = numOfLadder;
        this.snakeList = new ArrayList<>(numOfSnakes);
        this.ladderList = new ArrayList<>(numOfLadder);;
        this.playerQueue = new LinkedList<>();
        this.board = new Board(boardSize,1,boardSize);
        this.dice = new Dice(1,6,2);

        initGame();
    }

    public void initGame(){
        HashSet<String> startEndPosition = new HashSet<>();
        Random rd = new Random();

        //Generate random start and end values of snake


        for(int i = 0; i < numOfSnakes; i++) {
            while (true) {
                int start = rd.nextInt(2, board.getSize());
                int end = rd.nextInt(2, board.getSize());
                if (end >= start) {
                    continue;
                } else {
                    String key = String.valueOf(start) + end;
                    if (!startEndPosition.contains(key)) {
                        Snake sn = new Snake(start, end);
                        snakeList.add(sn);
                        startEndPosition.add(String.valueOf(start) + end);
                        break;
                    }

                }
            }
        }

        for(int i = 0; i < numOfLadder; i++) {
            while (true) {
                int start = rd.nextInt(2, board.getSize());
                int end = rd.nextInt(2, board.getSize());
                if (end <= start) {
                    continue;
                } else {
                    String key = String.valueOf(start) + end;
                    if (!startEndPosition.contains(key)) {
                        Ladder ld = new Ladder(start, end);
                        ladderList.add(ld);
                        startEndPosition.add(String.valueOf(start) + end);
                        break;
                    }

                }
            }
        }


    }

    public void addPlayer(Player player){
        playerQueue.add(player);
    }

    public void startGame(){

        while(true) {
            Player pl = playerQueue.poll();
            int diceRoll = dice.roll();

            assert pl != null;
            int oldPos = pl.getPosition();

            int newPos = oldPos + diceRoll;

            if (oldPos + diceRoll > board.getSize()) {
                pl.setPosition(pl.getPosition());
                playerQueue.add(pl);
            } else {
//                if (checkSnakeCounter(newPos) != -1) {
//                    int dd = checkSnakeCounter(newPos);
//                    pl.setPosition(snakeList.get(dd).getEnd());
//                    System.out.println("Player "+pl.getName()+ " moves from " + oldPos +" to new "+newPos+" with snake");
//
//                } else if (checkLadderCounter(newPos) != -1) {
//                    int dd = checkLadderCounter(newPos);
//                    pl.setPosition(ladderList.get(dd).getEnd());
//                    System.out.println("Player "+pl.getName()+ " moves from " + oldPos +" to new "+newPos+" with Ladder");
//
//                } else {
//                    pl.setPosition(newPos);
//                    System.out.println("Player "+pl.getName()+ " moves from " + oldPos +" to new "+newPos);
//
//                }

                pl.setPosition(getNewPosition(newPos,oldPos,pl));


                if (pl.getPosition() == board.getSize()) {
                    System.out.println("Player :" + pl.getName() + " won");
                    pl.setWon(true);
                    break;
                }
                else {
                    playerQueue.offer(pl);

                }
            }
        }

        if(playerQueue.size() < 2){
            System.out.println("Game over");
        }

    }

    public int getNewPosition(int pos, int old, Player pl){
        //return snakeList.stream().anyMatch(sn -> sn.getStart() == pos);
        for (Snake snake : snakeList) {
            if (snake.getStart() == pos) {
                System.out.println("Player "+pl.getName()+ " moves from " + old +" next : "+pos+ " after Snake "+snake.getEnd());
                return snake.getEnd();
            }
        }

        for (Ladder ladder : ladderList) {
            if (ladder.getStart() == pos) {
                System.out.println("Player "+pl.getName()+ " moves from " + old +" next : "+pos+ " after ladder "+ladder.getEnd());
                return ladder.getEnd();
            }
        }
        System.out.println("Player "+pl.getName()+ " moves from " + old +" to new "+pos);
        return pos;
    }

}
