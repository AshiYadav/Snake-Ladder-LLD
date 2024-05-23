package model;

public class Player {

    String name;

    int position  = 0;

    boolean isWon = false;

    public Player(String name, int position, boolean isWon) {
        this.name = name;
        this.position = position;
        this.isWon = isWon;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setWon(boolean won) {
        isWon = won;
    }
}
