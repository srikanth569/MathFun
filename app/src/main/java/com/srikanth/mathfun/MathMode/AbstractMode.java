package com.srikanth.mathfun.MathMode;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by srikanth on 2/13/15.
 */
public abstract class AbstractMode {

    //Variables
    protected int m = -1;
    protected int n = -1;
    protected String mQuestionString;
    protected ArrayList<Integer> mAnswerChoiceList = new ArrayList<>(4);
    protected int mCorrectAnswer = -1;

    //Constants
    protected static int max = 100;
    private static final int min = 1;
    private final Random rand = new Random();

    public abstract void setupQuestion();

    public abstract void setupAnswers();

    public ArrayList<Integer> getAnswerList() {
        return mAnswerChoiceList;
    }

    public int getmCorrectAnswer() {
        return mCorrectAnswer;
    }

    public String getmQuestionString() {
        return mQuestionString;
    }

    protected void generateRandomNumbers() {
        m = rand.nextInt((max - min) + 1) + min;
        n = rand.nextInt((max - min) + 1) + min;
        if (m <= 0 || n <= 0) {
            generateRandomNumbers();
        }
    }
}
