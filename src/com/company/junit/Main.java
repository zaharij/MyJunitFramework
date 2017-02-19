package com.company.junit;

import com.company.junit.annotations.MyTestClass;
import com.company.junit.exceptions.IllegalAnnotationException;
import com.company.junit.exceptions.MoreThanOneAnnotationException;
import com.company.junit.exceptions.NoTestClassException;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static boolean initMethodsInvoked = false;

    public static void main(String[] args) {

        Set<Class<?>> testClasses = getTestClasses();
        Iterator iter = testClasses.iterator();

        try {
            while(iter.hasNext()){
                startTests((Class) iter.next());
            }
            initMethodsInvoked = true;
        } catch (NoTestClassException e) {
            System.out.println(e.toString());
        } catch (InvocationTargetException e) {
            System.out.println(ANSI_RED + e.toString() + ANSI_RESET);
        } catch (IllegalAccessException e) {
            System.out.println(ANSI_RED + e.toString() + ANSI_RESET);
        } catch (MoreThanOneAnnotationException e) {
            System.out.println(ANSI_RED + e.toString() + ANSI_RESET);
        } catch (NoSuchMethodException e) {
            System.out.println(ANSI_RED + e.toString() + ANSI_RESET);
        } catch (IllegalAnnotationException e) {
            System.out.println(ANSI_RED + e.toString() + ANSI_RESET);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        printResultToConsole();
    }

    /**
     * returns all test classes in the package
     * @return
     */
    private static Set<Class<?>> getTestClasses() {
        Reflections reflections = new Reflections("com.company");
        return reflections.getTypesAnnotatedWith(MyTestClass.class);
    }

    /**
     * starts test methods
     * @param clas
     */
    private static void startTests(Class clas) throws NoTestClassException, InvocationTargetException
            , IllegalAccessException, MoreThanOneAnnotationException, NoSuchMethodException, IllegalAnnotationException, InstantiationException {

        MethodsToInvokeCollection methodsToInvokeCollection = new MethodsToInvokeCollection();
        Method[] methods = clas.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getDeclaredAnnotations().length > 1){
                throw new MoreThanOneAnnotationException();
            }
            methodsToInvokeCollection.setMethod(methods[i]);
        }

        Object object = clas.getConstructor().newInstance();
        if (!initMethodsInvoked) {
            for (Method method : methodsToInvokeCollection.getMethodsInit()) {
                method.invoke(object);
            }
        }
        for (Method method : methodsToInvokeCollection.getMethodsToInvoke()) {
            method.invoke(object);
        }
    }


    /**
     * prints result to console
     */
    private static void printResultToConsole(){
        ArrayList<StringBuilder> failedTests = JUnitLogic.getFailedTests();
        for (int i = 0; i < failedTests.size(); i++){
            System.out.println(ANSI_RED + failedTests.get(i) + ANSI_RESET);
        }
        System.out.println(JUnitLogic.getTestResults());
    }


}


