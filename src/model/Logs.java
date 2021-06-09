package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Logs implements Serializable {

    private ArrayList<String> logs;

    public Logs() {
        this.logs = new ArrayList<String>();
    }

    public void addDescriptions(String description) {
        this.logs.add(description);
    }

    @Override
    public String toString() {
        String result = "";
        result = logs.stream().map((act) -> act + "\n").reduce(result, String::concat);
        return result;
    }

    public void clear() {
        this.logs.clear();
    }

    public boolean isEmpty(){
        return logs.isEmpty();
    }
}
