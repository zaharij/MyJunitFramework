package com.company.junit;

import com.company.Test;
import com.company.junit.annotations.*;
import com.company.junit.exceptions.IllegalAnnotationException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;

/** MethodsToInvokeCollection
 * Collection of all test methods
 * Created by Zakhar on 17.02.2017.
 */
public class MethodsToInvokeCollection {
    private LinkedList<Method> methodsToInvoke = new LinkedList<>();
    private ArrayList<Method> methodsInit = new ArrayList<>();

    public ArrayList<Method> getMethodsInit() {
        return methodsInit;
    }

    public LinkedList<Method> getMethodsToInvoke() {
        return methodsToInvoke;
    }

    public void setMethod(Method method) throws NoSuchMethodException, IllegalAnnotationException {
        if (method.isAnnotationPresent(MyTestMethodInit.class)){
            if (!methodsInit.contains(method)){
                methodsInit.add(method);
            }
        } else if (method.isAnnotationPresent(MyTestMethod.class)){
            if(methodsToInvoke.contains(method)){
                return;
            } else {
                methodsToInvoke.add(method);
            }
        } else if (method.isAnnotationPresent(MyTestMethodBefore.class)){
            Method current = method.getDeclaringClass().getMethod(method.getAnnotation(MyTestMethodBefore.class).methodBefore());
            if (current.isAnnotationPresent(MyTestMethodInit.class)){
                throw new IllegalAnnotationException("Init method cannot has before or after methods");
            }
            if (!methodsToInvoke.contains(current)){
                methodsToInvoke.add(current);
            }
            methodsToInvoke.add(methodsToInvoke.indexOf(current), method);
        } else if (method.isAnnotationPresent(MyTestMethodAfter.class)){
            Method current = method.getDeclaringClass().getMethod(method.getAnnotation(MyTestMethodAfter.class).methodAfter());
            if (current.isAnnotationPresent(MyTestMethodInit.class)){
                throw new IllegalAnnotationException("Init method cannot has before or after methods");
            }
            if (!methodsToInvoke.contains(current)){
                methodsToInvoke.add(current);
            }
            methodsToInvoke.add(methodsToInvoke.indexOf(current) + 1, method);
        }
    }
}
