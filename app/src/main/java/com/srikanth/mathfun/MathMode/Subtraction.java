package com.srikanth.mathfun.MathMode;

import java.util.Collections;

/**
 * Created by srikanth on 2/13/15.
 */
public class Subtraction extends AbstractMode {
    private int limit = 100;

    @Override
    public void setupQuestion() {
        max = limit;
        generateRandomNumbers();
        if (m > n) {
            mQuestionString = m + " - " + n + " = ?";
        } else {
            mQuestionString = n + " - " + m + " = ?";
        }
    }

    @Override
    public void setupAnswers() {
        mAnswerChoiceList.clear();
        mCorrectAnswer = Math.abs(m - n);
        mAnswerChoiceList.add(mCorrectAnswer);
        mAnswerChoiceList.add(mCorrectAnswer + 1);
        mAnswerChoiceList.add(mCorrectAnswer + 10);
        mAnswerChoiceList.add(Math.abs(mCorrectAnswer - 10));
        Collections.shuffle(mAnswerChoiceList);
    }
}
