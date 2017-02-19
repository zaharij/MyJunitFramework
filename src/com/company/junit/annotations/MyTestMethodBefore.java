package com.company.junit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** MyTestMethodBefore
 * Annotates the method which calls before the current method
 * Created by Zakhar on 16.02.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyTestMethodBefore {
    String methodBefore();
}
