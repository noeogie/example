package org.eogie.example;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class DynamicLoader {

    public static void main(String[] args) {
        new DynamicLoader().start();
    }

    public void start() {
        try (URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {Paths.get("extern").toUri().toURL()})) {
            Class<?> dynamicClass = urlClassLoader.loadClass("library.DynamicClass");
            Object obj = dynamicClass.getConstructor().newInstance();

            Method concat = dynamicClass.getMethod("concat", String.class, String.class);
            String result1 = (String)concat.invoke(obj, "abc", "123");
            System.out.println("result1 = " + result1);

            Method sum = dynamicClass.getMethod("sum", Integer.TYPE, Integer.TYPE);
            int result2 = (int)sum.invoke(obj, 111, 222);
            System.out.println("result2 = " + result2);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
