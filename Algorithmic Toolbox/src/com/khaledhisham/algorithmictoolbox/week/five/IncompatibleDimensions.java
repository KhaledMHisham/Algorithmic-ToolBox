package com.khaledhisham.algorithmictoolbox.week.five;

public class IncompatibleDimensions extends Exception{
    public IncompatibleDimensions(String reason){
        super(reason);
    }
    public IncompatibleDimensions(String reason, Throwable cause){
        super(reason, cause);
    }
}
