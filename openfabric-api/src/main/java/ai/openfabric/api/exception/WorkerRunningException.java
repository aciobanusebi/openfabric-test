package ai.openfabric.api.exception;

public class WorkerRunningException extends RuntimeException {

    public WorkerRunningException(String id) {
        super(String.format("Worker with id '%s' is in the 'running' state!", id));
    }
}
