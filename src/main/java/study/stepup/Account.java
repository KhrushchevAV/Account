package study.stepup;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Account {

    @Getter
    private String name;

    private final HashMap<Currency, Integer> rest;

    private Deque<Command> commands = new ArrayDeque<>();

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
        // сохраним для undo
        String oldName = this.name;
        // если есть что сохранять
        if (!(oldName == null || oldName.isEmpty())) commands.push(()->{this.name = oldName;});
        //
        this.name = name;
    }

    public HashMap<Currency, Integer> getRest() {
        return new HashMap<>(rest);
    }

    public void setRest(Currency cur, Integer val) {
        if (val <0) {
            throw new IllegalArgumentException();
        }
        // сохраним для undo
        if (rest.containsKey(cur)) {
            Integer oldVal = rest.get(cur);
            commands.push(()->{rest.put(cur, oldVal);});
        }
        else {
            commands.push(()->{rest.remove(cur);});
        }
        //
        rest.put(cur, val);
    }

    public Account undo() {
        if (commands.isEmpty()) throw new IllegalStateException();
        commands.pop().perform();
        return this;
    }
}
