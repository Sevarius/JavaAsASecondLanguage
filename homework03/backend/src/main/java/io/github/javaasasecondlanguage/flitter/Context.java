package io.github.javaasasecondlanguage.flitter;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Context {

    private static final List<Flit> _lastTenMessages = new ArrayList<>();
    private static final List<Person> _registeredPeople = new ArrayList<>();
    static Integer i = 0;

    public static void clear() {
        _lastTenMessages.clear();
        _registeredPeople.clear();
        i = 0;
    }

    public static Responce registerPerson(String login) {
        //System.out.println("kekus-------------------------------------kekus");
        if (_registeredPeople.stream().map((x) -> x.name).collect(Collectors.toList()).contains(login)) {
            return Responce.makeResponce(null, "This name is already taken");
        }
        var p = new Person();
        p.token = Context._tokenGenerator();
        p.name = login;
        _registeredPeople.add(p);
        return Responce.makeResponce(Map.of("userName", p.name, "userToken", p.token), null);
    }

    public static Responce usersList() {
        if (_registeredPeople.size() == 0) {
            return Responce.makeResponce(new ArrayList<String>(), null);
        } else {
            return Responce.makeResponce(
                    _registeredPeople.stream()
                            .map((x) -> x.name)
                            .collect(Collectors.toList()), null);
        }
    }

    public static Responce lastTenFlits() {
        return Responce.makeResponce(_lastTenMessages, null);
    }

    public static Responce getAllFlitsFromThisPerson(String userName) {
        var thisPerson = _registeredPeople
                .stream()
                .filter((x) -> x.name.equals(userName))
                .collect(Collectors.toList());
        if (thisPerson.size() == 0) {
            return Responce.makeResponce(null, "User not found");
        } else {
            return Responce.makeResponce(thisPerson.get(0).getAllFlits(), null);
        }
    }

    public static Responce addNewFLit(String token, String content) {
        var thisPerson = _registeredPeople
                .stream()
                .filter((x) -> x.token.equals(token))
                .collect(Collectors.toList());
        if (thisPerson.size() == 0) {
            return Responce.makeResponce(null, "User not found");
        } else {
            var thisFlit = new Flit(content);
            thisPerson.get(0).addFlit(thisFlit);
            if (_lastTenMessages.size() == 10) {
                _lastTenMessages.remove(0);
            }
            _lastTenMessages.add(thisFlit);
            return Responce.makeResponce(true, null);
        }
    }

    public static Responce subscribe(String token, String userName) {
        var left = _registeredPeople
                .stream()
                .filter((x) -> x.token.equals(token))
                .collect(Collectors.toList());
        var right = _registeredPeople
                .stream()
                .filter((x) -> x.name.equals(userName))
                .collect(Collectors.toList());

        if (left.size() == 0 || right.size() == 0) {
            return Responce.makeResponce(null, "User not found");
        } else {
            left.get(0).addSubscription(right.get(0));
            return Responce.makeResponce(left.get(0), null);
        }
    }

    public static Responce unsubscribe(String token, String userName) {
        var left = _registeredPeople
                .stream()
                .filter((x) -> x.token.equals(token))
                .collect(Collectors.toList());
        var right = _registeredPeople
                .stream()
                .filter((x) -> x.name.equals(userName))
                .collect(Collectors.toList());

        if (left.size() == 0 || right.size() == 0) {
            return Responce.makeResponce(null, "User not found");
        } else {
            left.get(0).removeSubscription(right.get(0));
            return Responce.makeResponce(left.get(0), null);
        }
    }

    public static Responce getListSubscribers(String userToken) {
        var thisPerson = _registeredPeople
                .stream()
                .filter((x) -> x.token.equals(userToken))
                .collect(Collectors.toList());
        if (thisPerson.size() == 0) {
            return Responce.makeResponce(null, "User not found");
        } else {
            var res = thisPerson.get(0).getAllSubscribedPeopleNames();
            return Responce.makeResponce(res, null);
        }
    }

    public static Responce getListSubscriptions(String userToken) {
        var thisPerson = _registeredPeople
                .stream()
                .filter((x) -> x.token.equals(userToken))
                .collect(Collectors.toList());
        if (thisPerson.size() == 0) {
            return Responce.makeResponce(null, "User not found");
        } else {
            var res = thisPerson.get(0).getAllSubscriptions();
            return Responce.makeResponce(res, null);
        }
    }

    public static Responce getFeed(String userToken) {
        var thisPerson = _registeredPeople
                .stream()
                .filter((x) -> x.token.equals(userToken))
                .collect(Collectors.toList());
        if (thisPerson.size() == 0) {
            return Responce.makeResponce(null, "User not found");
        } else {
            var res = thisPerson.get(0).getFeed();
            return Responce.makeResponce(res, null);
        }
    }

    private static String _tokenGenerator() {
        return (i++).toString();
    }


}
