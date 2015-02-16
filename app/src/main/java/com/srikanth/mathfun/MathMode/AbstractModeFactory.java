package com.srikanth.mathfun.MathMode;

/**
 * Created by srikanth on 2/13/15.
 */
public class AbstractModeFactory {

    private static final int ADDITION = 0;
    private static final int SUBTRACTION = 1;
    private static final int MULTIPLICATION = 2;

    public static AbstractMode getMathMode(int mode) {
        AbstractMode mathMode = null;
        if (mode == ADDITION) {
            mathMode = new Addition();
        } else if (mode == SUBTRACTION) {
            mathMode = new Subtraction();
        } else if (mode == MULTIPLICATION) {
            mathMode = new Multiplication();
        }
        return mathMode;
    }
}
