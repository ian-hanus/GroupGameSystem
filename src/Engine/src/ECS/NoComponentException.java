package ECS;

public class NoComponentException extends EngineException {
    public NoComponentException(String error){
        super("NoComponent Error", error);
    }
}
