package model.plants;

import model.*;

public class Water extends Plant {

    public Water(Position position, World world) {
        super(position, world);
    }

    public Water(Position position) {
        super(position, null);
    }

    public Water(Water water) {
        super(water);
    }

    @Override
    public Water clone() {
        return new Water(this);
    }

    @Override
    public void initParam() {
        this.setPower(1000);
        this.setInitiative(0);
        this.setLiveLength(1000);
        this.setPowerToReproduce(100000);
    }

}
