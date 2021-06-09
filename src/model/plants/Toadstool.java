package model.plants;

import model.*;

public class Toadstool extends Plant {

    public Toadstool(Position position, World world) {
        super(position, world);
    }

    public Toadstool(Position position) {
        super(position, null);
    }

    public Toadstool(Toadstool toadstool) {
        super(toadstool);
    }

    @Override
    public Toadstool clone() {
        return new Toadstool(this);
    }

    @Override
    public void initParam() {
        this.setPower(1000);
        this.setInitiative(0);
        this.setLiveLength(1000);
        this.setPowerToReproduce(100000);
    }

}
