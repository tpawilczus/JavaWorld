package model.plants;

import model.*;

import java.util.ArrayList;

public abstract  class Plant extends Organism {

    public Plant(Position position, World world) {
        super(position, world);
    }

    public Plant(Plant plant) {
        super(plant);
    }

    public abstract Plant clone();

    @Override
    public ArrayList<Action> move() {
        ArrayList<Action> result = new ArrayList<Action>();
        Position newPosition;
        Plant newPlant;
        if (this.ifReproduce()) {
            newPosition = world.getFreeNeighboringPosition(this.position);
            if (world.isOnBoard(newPosition)) {
                newPlant = this.clone();
                newPlant.initParam();
                newPlant.position = newPosition;
                result.add(new Action(ActionEnum.ADD, newPosition, 0, newPlant));
                this.setPower(getPower()/2);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Action> collision(Organism CollisionOrganism) {
        ArrayList<Action> result = new ArrayList<>();
        return (result);
    }

    @Override
    public Position getLastPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    public ArrayList<Action> doNothing() {
        ArrayList<Action> result = new ArrayList<Action>();
        ArrayList<Position> helper  = new ArrayList<>();
        return result;
    }
}
