package ai.openfabric.api.exception;

public class WorkerNotFoundException extends RuntimeException {

    public WorkerNotFoundException(String id) {
        super(String.format("Worker with id '%s' was not found!", id));
    }
}
