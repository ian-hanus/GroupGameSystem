package components;

import entity.IEntity;

public abstract class Component implements IEntity {
    private int entityID;

    public int getID() {
        return entityID;
    }
}
