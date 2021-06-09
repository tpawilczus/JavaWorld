package model.animals;

import model.*;

public class Horse extends Animal {

    public Horse(Position position) {
        super(position, null);
    }

    public Horse(Horse horse) {
        super(horse);
    }

    @Override
    public Horse clone() {
        return new Horse(this);
    }

    @Override
    public void initParam() {
        this.setPower(5);
        this.setInitiative(7);
        this.setLiveLength(25);
        this.setPowerToReproduce(10);
    }
}

