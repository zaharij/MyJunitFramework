package com.company.junit.exceptions;

/**
 * Created by Zakhar on 17.02.2017.
 */
public class NoTestClassException extends Exception {
    private String e;

    public NoTestClassException (String e){
        this.e = e;
    }
    public NoTestClassException(){
        this("not test class..");
    }

    @Override
    public String toString() {
        return e;
    }
}
