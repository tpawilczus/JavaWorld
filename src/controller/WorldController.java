package controller;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import view.View;
import model.*;


public class WorldController {

    private World model;
    private View view;

    public WorldController(World model, View view) {
        this.model = model;
        this.view = view;

        view.setController(this);
    }

    public World getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public int getTurnNumber() {
        return model.getTurn();
    }

    public void performNextTurn() {
        model.makeTurn();
    }

    public String getActionLoggerReport() {
        return "" + model.getLogs();
    }

    public boolean isActionLoggerEmpty() {
        return model.getLogs().isEmpty();
    }

    public void clearActionLogger() {
        model.getLogs().clear();
    }

    public ArrayList<ArrayList<WorldCells>> getWorldMap() {
        ArrayList<ArrayList<WorldCells>> map = new ArrayList<>();
        ArrayList<WorldCells> pomWorldRow = new ArrayList<>();
        Organism org;
        Shape shape;
        int siteLenght = view.getSideMapLength();
        for (int y = 0; y < model.getWorldY(); y++) {
            for (int x = 0; x < model.getWorldX(); x++) {
                org = model.getOrganismFromPosition(new Position(x, y));
                shape = new Rectangle(x * siteLenght, y * siteLenght, siteLenght, siteLenght);

                if (org != null) {
                    pomWorldRow.add(new WorldCells(x, y, org.getColor(), false, shape));
                } else {
                    pomWorldRow.add(new WorldCells(x, y, Color.WHITE, true, shape));
                }
            }
            map.add(pomWorldRow);
            pomWorldRow = new ArrayList<>();
        }
        return map;
    }

}
