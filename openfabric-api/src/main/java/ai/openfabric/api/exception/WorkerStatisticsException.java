package ai.openfabric.api.exception;

public class WorkerStatisticsException extends RuntimeException {

    public WorkerStatisticsException(String id) {
        super(String.format("Cannot retrieve statistics for worker with id '%s'!", id));
    }
}
