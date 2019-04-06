package Components;

import Entities.IEntity;

public class Component implements IEntity {
    private int entityID;

    public int getID() {
        return entityID;
    }
}
