package io.github.javaasasecondlanguage.homework02.di;

public class Injector {
    private static Context _contex;

    public static void setContext(Context context) {
        _contex = context;
    }

    public static <T> T inject(Class<T> clazz) {
        //throw new RuntimeException("Not implemented");
        return (T) _contex.getElement(clazz.getName());
    }

    public static <T> T inject(Class<T> clazz, String qualifier) {
        //throw new RuntimeException("Not implemented");
        return (T) _contex.getElement(qualifier);
    }
}
