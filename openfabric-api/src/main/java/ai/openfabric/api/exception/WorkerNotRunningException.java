package ai.openfabric.api.exception;

public class WorkerNotRunningException extends RuntimeException {

    public WorkerNotRunningException(String id) {
        super(String.format("Worker with id '%s' is not in the 'running' state!", id));
    }
}
