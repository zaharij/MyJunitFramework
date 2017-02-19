package com.company;

import com.company.junit.annotations.*;

import static com.company.junit.JUnitLogic.ifEqualsFail;
import static com.company.junit.JUnitLogic.ifEqualsPass;

/**
 * Created by Zakhar on 17.02.2017.
 */
@MyTestClass
public class Test2 {

    @MyTestMethodAfter(methodAfter = "current1")
    public void after1(){
        String qwe = "qwe";
        ifEqualsFail(qwe, "qwe");
        System.out.println("test 2 - after current 1");
    }

    @MyTestMethodInit
    public void init(){
        String qwe = "qwe";
        ifEqualsFail(qwe, "qwet");
        System.out.println("test 2 - init method");
    }

    @MyTestMethodAfter(methodAfter = "current")
    public void after(){
        String qwe = "qwe";
        ifEqualsFail(qwe, "qwe");
        System.out.println("test 2 - after current");
    }

    @MyTestMethod
    public void current(){
        int t = 1 + 1;
        ifEqualsPass(t, 2);
        System.out.println("test 2 - current");
    }

    @MyTestMethodBefore(methodBefore = "current")
    public void before(){
        String t = "qwe";
        ifEqualsPass(t, "asd");
        System.out.println("test 2 - before current");
    }

    @MyTestMethodBefore(methodBefore = "current")
    public void before1(){
        String t = "qwe";
        ifEqualsPass(t, "qwe");
        System.out.println("test 2 - before current");
    }

    @MyTestMethod
    public void current1(){
        int t = 1 + 1;
        ifEqualsPass(t, 2);
        System.out.println("test 2 - current 1");
    }

    @MyTestMethodBefore(methodBefore = "current1")
    public void before2() {
        String t = "qwe";
        ifEqualsPass(t, "asd");
        System.out.println("test 2 - before current 1");
    }
}
