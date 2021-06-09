package model.animals;

import model.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal extends Organism {
    protected Position lastPosition;

    public Animal(Position position, World world) {
        super(position, world);
        this.lastPosition = new Position(position);
    }

    public Animal(Animal animal){
        super(animal);
        this.lastPosition = new Position(animal.lastPosition);
    }

    public abstract Animal clone();

    @Override
    public ArrayList<Action> collision(Organism collisionOrganism) {
        ArrayList<Action> result = new ArrayList<>();
        ArrayList<Action> helper = new ArrayList<>();
        Position newPosition = null;
        Animal newAnimal = null;
        if (collisionOrganism != null) {
            if ( this.getClass().equals(collisionOrganism.getClass())  ){
                result.add(new Action(ActionEnum.MOVE, this.lastPosition, 0, this));
                newPosition = world.getFreeNeighboringPosition(this.position);

                if (world.isOnBoard(newPosition)) {
                    newAnimal = this.clone();
                    newAnimal.initParam();
                    newAnimal.position = newPosition;
                    result.add(new Action(ActionEnum.ADD, newPosition, 0, newAnimal));
                }
            } else {
                helper = collisionOrganism.diplomacy(this);
                if (helper.isEmpty()) {
                    result = collisionOrganism.consequences(this);
                } else {
                    result = helper;
                }
            }
        }
        return result;
    }

    public void setPosition(Position position) {
        this.lastPosition = this.position;
        this.position = position;
    }


    public Position getLastPosition() {
        return this.lastPosition;
    }

    @Override
    public ArrayList<Action> move() {
        ArrayList<Action> result = new ArrayList<Action>();
        ArrayList<Position> helper  = new ArrayList<>();
        helper = world.getListOfNeighboringPositions(position);
        if (helper != null) {
            Position random = helper.get(new Random().nextInt(helper.size()));
            result.add(new Action(ActionEnum.MOVE, random, 0, this));
        }
        Position newPosition;
        Animal newAnimal;
        if (this.ifReproduce()) {
            newPosition = world.getFreeNeighboringPosition(this.position);
            if (world.isOnBoard(newPosition)) {
                newAnimal = this.clone();
                newAnimal.initParam();
                newAnimal.position = newPosition;
                result.add(new Action(ActionEnum.ADD, newPosition, 0, newAnimal));
                this.setPower(getPower()/2);
            }
        }
        return result;
    }
    public ArrayList<Action> doNothing() {
        ArrayList<Action> result = new ArrayList<Action>();
        ArrayList<Position> helper  = new ArrayList<>();
        return result;
    }

}
