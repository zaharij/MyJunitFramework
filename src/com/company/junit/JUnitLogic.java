package com.company.junit;

import java.util.ArrayList;

/** JUnitLogic
 * This class describes all methods, which controls test methods
 * Created by Zakhar on 17.02.2017.
 */
public class JUnitLogic {
    private static int passed = 0;
    private static int failed = 0;
    private static ArrayList<StringBuilder> failedTests = new ArrayList<>();

    /**
     * checks if given objects are equal
     * @param tCurrent
     * @param tExpected
     * @param <T>
     */
    public static <T> void ifEqualsPass(T tCurrent, T tExpected){
        if (tCurrent.equals(tExpected)){
            passed();
        } else {
            failed();
        }

    }

    /**
     * checks if given objects are different
     * @param tCurrent
     * @param tExpected
     * @param <T>
     */
    public static <T> void ifEqualsFail(T tCurrent, T tExpected){
        if (tCurrent.equals(tExpected)){
            failed();
        } else {
            passed();
        }
    }

    private static void failed(){
        failed++;
        failedTests.add(new StringBuilder().append("test failed: ").append(String.valueOf(Thread.currentThread().getStackTrace()[3])));
    }

    private static void passed(){
        passed++;
    }

    public static StringBuilder getTestResults(){
        return new StringBuilder().append("passed: ").append(passed).append("\n").append("failed: ").append(failed);
    }

    public static ArrayList<StringBuilder> getFailedTests() {
        return failedTests;
    }
}
