package io.github.javaasasecondlanguage.homework02.di;

import java.util.*;

public class Context {

    private final Map<String, Object> _internalMap = new HashMap<>();
    private final List<Object> _internalArray = new ArrayList<>();

    public Context() {
        Injector.setContext(this);
    }

    public <T> Context register(T object, String qualifier) {
        //throw new RuntimeException("Not implemented");
        if (!_internalMap.containsKey(qualifier))
            _internalMap.put(qualifier, object);
        return this;
    }

    public <T> Context register(T object) {
        //throw new RuntimeException("Not implemented");
        _internalArray.stream().filter(x -> );
        if (_internalArray.get(0) instanceof T) {
            System.out.println("kek");
        }

        return this;
    }

    public Object getElement(String key) {
        return _internalMap.get(key);
    }
}
