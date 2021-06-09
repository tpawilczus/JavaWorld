package model.plants;

import model.*;

import java.util.ArrayList;

public class Grass extends Plant {

    public Grass(Position position, World world) {
        super(position, world);
    }

    public Grass(Position position) {
        super(position, null);
    }

    public Grass(Grass grass){
        super(grass);
    }

    @Override
    public Grass clone() {
        return new Grass(this);
    }

    @Override
    public void initParam() {
        this.setPower(0);
        this.setInitiative(1);
        this.setLiveLength(7);
        this.setPowerToReproduce(3);
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
