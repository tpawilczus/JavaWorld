package model.animals;

import model.*;

import java.util.ArrayList;

public class Alien extends Animal{

    public Alien(Position position) {
        super(position, null);
    }

    public Alien(Alien alien) {
        super(alien);
    }


    @Override
    public Animal clone() {
        return new Alien(this);
    }

    @Override
    public void initParam() {
        this.setPower(10000);
        this.setInitiative(0);
        this.setPowerToReproduce(100000);
        this.setLiveLength(100);
    }
    @Override
    public ArrayList<Action> collision(Organism collisionOrganism) {
        ArrayList<Action> result = new ArrayList<>();
        ArrayList<Action> helper = new ArrayList<>();
        return result;
    }

}
