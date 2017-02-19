package com.company.junit.exceptions;

/** IllegalAnnotationException
 * Created by Zakhar on 19.02.2017.
 */
public class IllegalAnnotationException extends Exception {
    private String e;

    public IllegalAnnotationException (String e){
        this.e = e;
    }
    public IllegalAnnotationException(){
        this("Illegal annotation!");
    }

    @Override
    public String toString() {
        return e;
    }
}
