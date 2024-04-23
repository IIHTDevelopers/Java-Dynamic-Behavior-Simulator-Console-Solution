package com.streams.assessment;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public class ReflectionReplacement {
    private void secretMethod() {
        System.out.println("Secret method accessed!");
    }

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Method method = ReflectionReplacement.class.getDeclaredMethod("secretMethod");
        method.setAccessible(true);
        MethodHandle handle = lookup.unreflect(method);
        handle.invoke(new ReflectionReplacement());
    }
}
