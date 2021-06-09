package main;

import controller.WorldController;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import model.*;
import view.View;

public class Main {

    public static void main(String args[]) {

        Properties prop = new Properties();
        World model;
        try (FileReader reader = new FileReader("C:\\Users\\Oem\\Documents\\GitHub\\009_projekt-tpawilczus\\src\\controller\\app.properties")) {
            prop.load(reader);
            model = new World();

            String propName, propValue;
            int propValInt;
            OrganismFactory factory = new OrganismFactory();
            Organism org;
            for (SpeciesEnum sp : SpeciesEnum.values()) {
                propName = sp.name().toLowerCase();
                propValue = prop.getProperty(propName);
                propValInt = Integer.parseInt(propValue);
                for (int i = 0; i < propValInt; i++) {
                    org = factory.createOrganism(sp, model.getFreePositionInWorld());
                    if (org != null) {
                        org.setWorld(model);
                        model.addOrganism(org);
                    }
                }
            }
            View view = new View();
            new WorldController(model, view);

        } catch (IOException e) {
            System.err.println("app.properties: " + e);
        } catch (NumberFormatException e) {
            System.err.println("ParseInt error: " + e);
        }
    }
}
