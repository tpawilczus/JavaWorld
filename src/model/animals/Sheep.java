package model.animals;

import model.*;

public class Sheep extends Animal {

    public Sheep(Position position) {
        super(position, null);
    }

    public Sheep(Sheep sheep){
        super(sheep);
    }

    @Override
    public Animal clone() {
        return new Sheep(this);
    }

    @Override
    public void initParam() {
        this.setPower(3);
        this.setInitiative(3);
        this.setLiveLength(10);
        this.setPowerToReproduce(6);
    }
}
