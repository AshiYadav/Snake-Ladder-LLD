package model;

import java.util.Random;

public class Dice {

    int minVal = 1;
    int maxVal = 6;

    int currentVal = 2;

    public Dice(int minVal, int maxVal, int currentVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.currentVal = currentVal;
    }

    public int roll(){
        Random ran = new Random();
        // +1 because we have to include max val
        return ran.nextInt(minVal,maxVal+1);
    }
}
