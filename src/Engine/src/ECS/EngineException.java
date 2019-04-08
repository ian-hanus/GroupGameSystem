package ECS;

public abstract class EngineException extends Exception {
    private String errorType;

    public EngineException(String errorType, String error){
        super(error);
        this.errorType = errorType;
    }

    protected String getErrorType(){
        return errorType;
    }
}
