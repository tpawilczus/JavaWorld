package model;

public class Action {

    private ActionEnum action;
    private Position position;
    private int value;
    private Organism organism;

    public Action(ActionEnum action, Position position, int value, Organism organism) {
        this.action = action;
        this.position = position;
        this.value = value;
        this.organism = organism;
    }

    public ActionEnum getAction() {
        return action;
    }

    public Position getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public Organism getOrganism() {
        return organism;
    }

    @Override
    public String toString() {
        switch(action){
            case ADD:
                return organism.getClass().getSimpleName() + ": dodanie na pozycję: " + position;
            case INCREASE_POWER:
                return organism.getClass().getSimpleName() + ": zwiększenie siły o: " + value;
            case MOVE:
                return organism.getClass().getSimpleName() + ": zmiana pozycji z: " + organism.getLastPosition() + " na: " +position;
            case DELETE:
                return organism.getClass().getSimpleName() + ": usunięcie z pozycji: " + position;
            default:
                return "";
        }
    }


}