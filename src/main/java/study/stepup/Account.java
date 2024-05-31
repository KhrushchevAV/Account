package study.stepup;

import lombok.Getter;

import java.util.HashMap;

public class Account {

    @Getter
    private String name;

    private final HashMap<Currency, Integer> rest;

    public Account(String name) {
        setName(name);
        rest = new HashMap<>();
    }

    public void print() {
        System.out.println("Account.name="+this.name);
        System.out.println(" rest:");
        rest.forEach((key, val)-> System.out.println(" " + key + "\t" + val));
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public HashMap<Currency, Integer> getRest() {
        return new HashMap<>(rest);
    }

    public void setRest(Currency cur, Integer val) {
        if (val <0) {
            throw new IllegalArgumentException();
        }
        rest.put(cur, val);
    }

    public void undo() {

    }
}
