package model;

public class Board {

    int size;
    int start = 1;
    int end;


    public Board(int size, int start, int end) {
        this.size = size;
        this.start = start;
        this.end = size;
    }

    public int getSize() {
        return size;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
