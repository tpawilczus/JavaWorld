package model;

import model.animals.*;
import model.plants.*;
import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @org.junit.jupiter.api.Test
    void getOrganisms() {
        World world = new World();
        Wolf wolf = new Wolf(world.getFreePositionInWorld());
        Toadstool toadstool = new Toadstool(world.getFreePositionInWorld());
        world.addOrganism(wolf);
        world.addOrganism(toadstool);
        assertEquals(2, world.getOrganisms().size());
    }

    @org.junit.jupiter.api.Test
    void collisionWolfAndSheep() {
        World world = new World();
        Wolf wolf = new Wolf(world.getFreePositionInWorld());
        Sheep sheep = new Sheep(world.getFreePositionInWorld());
        wolf.initParam();
        sheep.initParam();
        world.addOrganism(wolf);
        world.addOrganism(sheep);
        assertEquals("[Sheep: usunięcie z pozycji: Position{x=-10, y=-10}]", wolf.collision(sheep).toString());
    }

    @org.junit.jupiter.api.Test
    void collisionHorseAndToadstool() {
        World world = new World();
        Horse horse = new Horse(world.getFreePositionInWorld());
        Toadstool toadstool = new Toadstool(world.getFreePositionInWorld());
        horse.initParam();
        toadstool.initParam();
        world.addOrganism(horse);
        world.addOrganism(toadstool);
        assertEquals("[Horse: usunięcie z pozycji: Position{x=-10, y=-10}]", horse.collision(toadstool).toString());
    }

    @org.junit.jupiter.api.Test
    void collisionBoarAndDandelion() {
        World world = new World();
        Boar boar = new Boar(world.getFreePositionInWorld());
        Dandelion dandelion = new Dandelion(world.getFreePositionInWorld());
        boar.initParam();
        dandelion.initParam();
        world.addOrganism(boar);
        world.addOrganism(dandelion);
        assertEquals("[Dandelion: usunięcie z pozycji: Position{x=-10, y=-10}]", boar.collision(dandelion).toString());
    }

    @org.junit.jupiter.api.Test
    void collisionBoarAndWolf() {
        World world = new World();
        Boar boar = new Boar(world.getFreePositionInWorld());
        Wolf wolf = new Wolf(world.getFreePositionInWorld());
        boar.initParam();
        wolf.initParam();
        world.addOrganism(boar);
        world.addOrganism(wolf);
        assertEquals("[Wolf: usunięcie z pozycji: Position{x=-10, y=-10}]", boar.collision(wolf).toString());
    }

    @org.junit.jupiter.api.Test
    void grassClone() {
        World world = new World();
        Grass grass = new Grass(world.getFreePositionInWorld());
        grass.initParam();
        world.addOrganism(grass);
        world.addOrganism(grass.clone());
        assertEquals(2, world.getOrganisms().size());
    }

    @org.junit.jupiter.api.Test
    void collisionAlienAndWolf() {
        World world = new World();
        Alien alien = new Alien(world.getFreePositionInWorld());
        Wolf wolf = new Wolf(world.getFreePositionInWorld());
        alien.initParam();
        wolf.initParam();
        world.addOrganism(alien);
        world.addOrganism(wolf);
        assertEquals("[]", alien.collision(wolf).toString());
    }

    @org.junit.jupiter.api.Test
    void collisionAlienAndDandelion() {
        World world = new World();
        Alien alien = new Alien(world.getFreePositionInWorld());
        Dandelion dandelion = new Dandelion(world.getFreePositionInWorld());
        alien.initParam();
        dandelion.initParam();
        world.addOrganism(alien);
        world.addOrganism(dandelion);
        assertEquals("[]", alien.collision(dandelion).toString());
    }
}