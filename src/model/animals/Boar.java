package model.animals;

import model.*;

public class Boar extends Animal {

    public Boar(Position position) {
        super(position, null);
    }

    public Boar(Boar boar) {
        super(boar);
    }

    @Override
    public Boar clone() {
        return new Boar(this);
    }

    @Override
    public void initParam() {
        this.setPower(9);
        this.setInitiative(6);
        this.setLiveLength(18);
        this.setPowerToReproduce(13);
    }
}

