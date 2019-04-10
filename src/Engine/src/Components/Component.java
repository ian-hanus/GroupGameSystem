package Engine.src.Components;

import Entity.IEntity;

public abstract class Component implements IEntity {
    private int entityID;

    public int getID() {
        return entityID;
    }
}
