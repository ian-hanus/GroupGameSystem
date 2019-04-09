package ecs;

public class NoEntityException extends EngineException {
    public NoEntityException(String error){
        super("NoEntity Error", error);
    }
}
