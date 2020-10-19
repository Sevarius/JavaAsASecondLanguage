package io.github.javaasasecondlanguage.lecture05.practice1;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class ReflectionPlayground {

    @Test
    void createA() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // create A instance using reflection
        // hint: A.class
        var a = A.class.getDeclaredConstructor().newInstance();
        System.out.println(a);
    }

    @Test
    void createB() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // create B instance using reflection
        // hint: change access
        var b = B.class.getDeclaredConstructor().newInstance();
        System.out.println(b);
    }

    @Test
    void listMethodsA() {
        var aList = B.class.getMethods();
        for (var m : aList) {
            System.out.println(m.getName());
        }
    }

    @Test
    void listMethodsAnnotatedWithOverride() {
        var aList = C.class.getMethods();
        for (var m : aList) {
            var annList = m.getAnnotations();
            if (annList.length == 1 && annList[0].getClass().getName() == "Override")
                System.out.println();
        }
    }

    static class A {
        String string;
        Integer integer;
    }

    static class B {
        String string;
        Integer integer;

        private B() {
        }
    }

    static class C {
        String string;
        Integer integer;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            C c = (C) o;
            return Objects.equals(string, c.string)
                   && Objects.equals(integer, c.integer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(string, integer);
        }
    }
}
