package io.github.javaasasecondlanguage.flitter;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private final List<Flit> _flits = new ArrayList<>();
    private final List<Person> _subscribedPeople = new ArrayList<>();
    private final List<Person> _subscriptions = new ArrayList<>();

    @JsonProperty("token")
    public String token;

    @JsonProperty("userName")
    public String name;

    public void addFlit(Flit flit) {
        flit.setUser(this);
        _flits.add(flit);
    }

    public List<Flit> getAllFlits() {
        return _flits;
    }

    public void addSubscription(Person person) {
        _subscriptions.add(person);
        person.addSubscribed(this);
    }

    public void addSubscribed(Person person) {
        _subscribedPeople.add(person);
    }

    public void removeSubscription(Person person) {
        _subscriptions.remove(person);
        person.removeSubscribed(this);
    }

    public void removeSubscribed(Person person) {
        _subscribedPeople.remove(person);
    }

    public List<String> getAllSubscribedPeopleNames() {
        return _subscribedPeople.stream().map((x) -> x.name).collect(Collectors.toList());
    }

    public List<String> getAllSubscriptions() {
        return _subscriptions.stream().map((x) -> x.name).collect(Collectors.toList());
    }

    public List<Flit> getFeed() {
        List<Flit> res = new ArrayList<>();
        _subscriptions.forEach((x) -> res.addAll(x._flits));
        return res;
    }


}
