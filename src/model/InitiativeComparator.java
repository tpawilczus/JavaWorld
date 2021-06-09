package model;

import java.util.Comparator;

public class InitiativeComparator implements Comparator<Organism> {
    @Override
    public int compare(Organism o1, Organism o2) {
        return o2.getInitiative() - o1.getInitiative();
    }
}

