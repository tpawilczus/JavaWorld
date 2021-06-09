package model;

import model.animals.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class World implements Serializable {
    private int worldX;
    private int worldY;
    private int turn;
    private ArrayList<Organism> organisms;
    private ArrayList<Organism> newOrganisms;
    private Logs logs;

    public World() {
        this.worldX = 20;
        this.worldY = 20;
        this.turn = 0;
        this.organisms = new ArrayList<Organism>();
        this.newOrganisms = new ArrayList<Organism>();
        this.logs =  new Logs();
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public void setOrganisms(ArrayList<Organism> organisms) {
        this.organisms = organisms;
    }

    public ArrayList<Organism> getNewOrganisms() {
        return newOrganisms;
    }

    public void setNewOrganisms(ArrayList<Organism> newOrganisms) {
        this.newOrganisms = newOrganisms;
    }

    public Logs getLogs() {
        return logs;
    }

    public void setLogs(Logs logs) {
        this.logs = logs;
    }

    public void addOrganism(Organism o) {
        this.organisms.add(o);
    }

    public ArrayList<Position> getListOfNeighboringPositions(Position position) {
        ArrayList<Position> NeighboringPositions = new ArrayList<>();
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++) NeighboringPositions.add(new Position(i, j));
        for (Position p : NeighboringPositions) {
            p.setX(p.getX() + position.getX());
            p.setY(p.getY() + position.getY());
        }
        NeighboringPositions.removeIf(p -> !isOnBoard(p));
        return NeighboringPositions;
    }

    public boolean isOnBoard(Position p) {
        return (p.getX() >= 0 && p.getY() >= 0 && p.getX() < this.worldX && p.getY() < this.worldY);
    }

    public boolean isAlienNeighbour(Position p) {
        boolean result = false;
        Organism helper;
        int x = p.getX();
        int y = p.getY();
        for(int i=x-2;i<x+3;i++)
            for(int j=y-2;j<y+3;j++) {
                if(x!=i && y!=j) {
                    helper = getOrganismFromPosition(i, j);
                    if (helper instanceof Alien) {
                        result = true;
                    }
                }
            }
        return result;
    }


    public void makeTurn() {
        ArrayList<Action> actions = new ArrayList<Action>();
        Position helper;
        Organism collisionOrganism;

        for (Organism o : this.organisms) {
            if (isOnBoard(o.getPosition())) {
                if(isAlienNeighbour(o.getPosition())) {
                    o.setPower(o.getPower()-1);
                    o.setLiveLength(o.getLiveLength()+1);
                    actions = o.doNothing();
                }
                else {
                    actions = o.move();
                    for (Action a : actions) {
                        doLogs(a);
                    }
                    actions.clear();
                    helper = o.getPosition();
                    o.setPosition(new Position(-10, -10));
                    collisionOrganism = getOrganismFromPosition(helper);
                    o.setPosition(helper);
                    actions = o.collision(collisionOrganism);
                    for (Action a : actions) {
                        doLogs(a);
                    }
                }
            }
            actions.clear();
        }
        for(Organism org : this.organisms) {
            org.setPower(org.getPower()+1);
            org.setLiveLength(org.getLiveLength()-1);
            if(org.getLiveLength()<1) {
                org.setPosition(new Position(-10,-10));
            }
        }
        death();
        organisms.addAll(newOrganisms);
        Collections.sort(organisms, new InitiativeComparator());
        this.newOrganisms.clear();
        this.turn++;

    }
    public Position getFreePositionInWorld() {
        ArrayList<Position> freePosition = new ArrayList<>();
        for (int y = 0; y < this.worldY; y++) {
            for (int x = 0; x < this.worldX; x++) {
                if (getOrganismFromPosition(new Position(x, y)) == null)
                {
                    freePosition.add(new Position(x, y));
                }
            }
        }
        if (freePosition.isEmpty()) {
            return (new Position(-10, -10));
        }
        return freePosition.get((new Random()).nextInt(freePosition.size()));
    }

    public Organism getOrganismFromPosition(Position position) {
        Organism helperOrg = null;
        organisms = getOrganisms();
        for (Organism o : organisms) {
            if (o.getPosition().equals(position)) {
                helperOrg = o;
                break;
            }
        }
        return helperOrg;
    }
    public Organism getOrganismFromPosition(int x, int y) {
        Organism helperOrg = null;
        organisms = getOrganisms();
        for (Organism o : organisms) {
            if (o.getPosition().getX()==x && o.getPosition().getY()==y) {
                helperOrg = o;
                break;
            }
        }
        return helperOrg;
    }

    public ArrayList<Organism> getOrganismsFromPosition(Position position) {
        ArrayList<Organism> helper = new ArrayList<>();
        organisms = getOrganisms();
        for (Organism o : organisms) {
            if (o.getPosition().equals(position)) {
                helper.add(o);
                break;
            }
        }
        return helper;
    }
    public void death(){
        ArrayList<Organism> helper = new ArrayList<>();
        helper = getOrganismsFromPosition(new Position(-10,-10));
        organisms.removeAll(helper);
    }

    public Position getFreeNeighboringPosition(Position position) {

        ArrayList<Position> pomNeighboringPositions = getListOfNeighboringPositions(position);
        for (Position pos : pomNeighboringPositions) {
            if (getOrganismFromPosition(pos) == null) {
                return pos;
            }
        }
        return new Position(-10, -10);
    }


    private void doLogs(Action action) {
        switch (action.getAction()) {
            case MOVE:
                logs.addDescriptions(action.getOrganism().getClass().getSimpleName() +
                        ": zmiana pozycji z: " + action.getOrganism().getPosition() + " na: " +
                        action.getPosition());
                action.getOrganism().setPosition(action.getPosition());
                break;
            case DELETE:
                logs.addDescriptions( action.getOrganism().getClass().getSimpleName() +
                        ": usunięcie z pozycji: " + action.getOrganism().getPosition());
                action.getOrganism().setPosition(new Position(-10, -10));
                break;
            case ADD:
                logs.addDescriptions(action.getOrganism().getClass().getSimpleName() +
                        ": dodanie na pozycję: " + action.getPosition());
                this.newOrganisms.add(action.getOrganism());
                break;
            case INCREASE_POWER:
                logs.addDescriptions(action.getOrganism().getClass().getSimpleName() +
                        ": zwiększenie siły o: " + action.getValue());
                action.getOrganism().setPower(action.getOrganism().getPower() + action.getValue());
                break;
        }
    }

    @Override
    public String toString() {
        return "World{" +
                "Xs=" + worldX +
                ", Ys=" + worldY +
                ", turn=" + turn +
                '}';
    }
}

