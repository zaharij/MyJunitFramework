package com.company.junit.exceptions;

/**
 * Created by Zakhar on 19.02.2017.
 */
public class MoreThanOneAnnotationException extends Exception {
    private String e;

    public MoreThanOneAnnotationException (String e){
        this.e = e;
    }
    public MoreThanOneAnnotationException(){
        this("More than one test annotation! You can use only one test annotation for methods!");
    }

    @Override
    public String toString() {
        return e;
    }
}
