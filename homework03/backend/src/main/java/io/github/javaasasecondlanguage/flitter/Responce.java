package io.github.javaasasecondlanguage.flitter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Responce {

    @JsonProperty("data")
    public Object data;
    @JsonProperty("errorMessage")
    public Object errorMessage;

    public Responce(Object a, Object b) {
        data = a;
        errorMessage = b;
    }

    public static Responce makeResponce(Object a, Object b) {
        return new Responce(a, b);
    }
}
