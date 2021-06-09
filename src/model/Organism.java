package model;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Organism implements Serializable {
    protected int power;
    protected int initiative;
    public Position position;
    protected int liveLength;
    protected int powerToReproduce;
    protected World world;
    protected Color color;

    public Organism(Position position, World world) {
        this.position = new Position(position);
        this.world = world;
    }

    public Organism(Organism organism) {
        this.power = organism.power;
        this.initiative = organism.initiative;
        this.position = new Position(organism.position);
        this.powerToReproduce = organism.powerToReproduce;
        this.liveLength = organism.liveLength;
        this.world = organism.world;
        this.color = organism.color;
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLiveLength() {
        return liveLength;
    }

    public void setLiveLength(int liveLength) {
        this.liveLength = liveLength;
    }

    public int getPowerToReproduce() {
        return powerToReproduce;
    }

    public void setPowerToReproduce(int powerToReproduce) {
        this.powerToReproduce = powerToReproduce;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void initParam();

    public abstract Position getLastPosition();

    public abstract ArrayList<Action> move();

    public abstract ArrayList<Action> doNothing();

    public abstract Organism clone();

    public abstract ArrayList<Action> collision(Organism collisionOrganism);

    public ArrayList<Action> diplomacy(Organism atackingOrganism) {
        ArrayList<Action> result = new ArrayList<Action>();
        return result;
    }

    public ArrayList<Action> consequences(Organism atackingOrganism) {
        ArrayList<Action> result = new ArrayList<Action>();
        if (this.getPower() > atackingOrganism.getPower()) {
            result.add(new Action(ActionEnum.DELETE, new Position(-10, -10), 0, atackingOrganism));
        } else {
            result.add(new Action(ActionEnum.DELETE, new Position(-10, -10), 0, this));
        }
        return result;
    }

    public boolean ifReproduce() {
        boolean result = false;
        if(this.getPower()>this.getPowerToReproduce())
            result = true;
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                ": power: " + power +
                " initiative: " + initiative +
                " live length: " + liveLength +
                " position: " + position;
    }
}
