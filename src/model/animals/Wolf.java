package model.animals;

import model.*;

public class Wolf extends Animal {

    public Wolf(Position position) {
        super(position, null);
    }

    public Wolf(Wolf wolf) {
        super(wolf);
    }

    @Override
    public Wolf clone() {
        return new Wolf(this);
    }

    @Override
    public void initParam() {
        this.setPower(8);
        this.setInitiative(5);
        this.setLiveLength(20);
        this.setPowerToReproduce(16);
    }
}

