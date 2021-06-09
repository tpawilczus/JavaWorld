package model.plants;

import model.*;

import java.util.ArrayList;

public class Dandelion extends Plant {

    public Dandelion(Position position, World world) {
        super(position, world);
    }

    public Dandelion(Position position) {
        super(position, null);
    }

    public Dandelion(Dandelion dandelion) {
        super(dandelion);
    }

    @Override
    public Dandelion clone() {
        return new Dandelion(this);
    }

    @Override
    public void initParam() {
        this.setPower(0);
        this.setInitiative(2);
        this.setLiveLength(6);
        this.setPowerToReproduce(2);
    }

    @Override
    public ArrayList<Action> move() {
        ArrayList<Action> result = new ArrayList<Action>();
        for (int i = 0; i < 3; i++) {
            result.addAll(super.move());
            if (!result.isEmpty()) {
                break;
            }
        }
        return result;
    }
}
