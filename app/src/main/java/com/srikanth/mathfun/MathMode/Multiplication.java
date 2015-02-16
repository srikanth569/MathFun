package com.srikanth.mathfun.MathMode;

import java.util.Collections;

/**
 * Created by srikanth on 2/13/15.
 */
public class Multiplication extends AbstractMode {
    private int limit = 15;

    @Override
    public void setupQuestion() {
        max = limit;
        generateRandomNumbers();
        mQuestionString = m + " * " + n + " = ?";
    }

    @Override
    public void setupAnswers() {
        mAnswerChoiceList.clear();
        mCorrectAnswer = m * n;
        mAnswerChoiceList.add(mCorrectAnswer);
        mAnswerChoiceList.add(mCorrectAnswer + 1);
        mAnswerChoiceList.add(mCorrectAnswer + 10);
        mAnswerChoiceList.add(Math.abs(mCorrectAnswer - 10));
        Collections.shuffle(mAnswerChoiceList);
    }
}
