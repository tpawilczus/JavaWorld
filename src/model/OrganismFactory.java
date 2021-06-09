package model;

import java.awt.Color;
import model.animals.*;
import model.plants.*;


public class OrganismFactory {

    public Organism createOrganism(SpeciesEnum species, Position position) {
        Organism organism = null;
        if (position.getX() != -10 && position.getY() != -10) {
            switch (species) {
                case SHEEP:
                    organism = new Sheep(position);
                    organism.setColor(Color.LIGHT_GRAY);
                    break;
                case GRASS:
                    organism = new Grass(position);
                    organism.setColor(Color.GREEN);
                    break;
                case WOLF:
                    organism = new Wolf(position);
                    organism.setColor(Color.DARK_GRAY);
                    break;
                case DANDELION:
                    organism = new Dandelion(position);
                    organism.setColor(Color.YELLOW);
                    break;
                case TOADSTOOL:
                    organism = new Toadstool(position);
                    organism.setColor(Color.RED);
                    break;
                case ALIEN:
                    organism = new Alien(position);
                    organism.setColor(Color.CYAN);
                    break;
                case HORSE:
                    organism = new Horse(position);
                    organism.setColor(Color.PINK);
                    break;
                case BOAR:
                    organism = new Boar(position);
                    organism.setColor(Color.MAGENTA);
                    break;
                case WATER:
                    organism = new Water(position);
                    organism.setColor(Color.BLUE);
                    break;
            }
            organism.initParam();
        }
        return organism;
    }
}
