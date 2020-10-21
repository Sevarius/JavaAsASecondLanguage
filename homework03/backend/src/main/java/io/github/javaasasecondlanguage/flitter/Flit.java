package io.github.javaasasecondlanguage.flitter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Flit {

    @JsonProperty("content")
    public String content;
    private Person _user;

    public Flit(String comment) {
        this.content = comment;
    }

    @JsonProperty("userName")
    public String getUserName() {
        return _user.name;
    }

    public void setUser(Person person) {
        _user = person;
    }
}
