package ai.openfabric.api.advice;

import ai.openfabric.api.exception.WorkerNotFoundException;
import ai.openfabric.api.exception.WorkerNotRunningException;
import ai.openfabric.api.exception.WorkerRunningException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WorkerAdvice {
    @ResponseBody
    @ExceptionHandler(WorkerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseMessage workerNotFoundHandler(WorkerNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(WorkerNotRunningException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseMessage workerNotRunningHandler(WorkerNotRunningException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(WorkerRunningException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseMessage workerRunningHandler(WorkerRunningException ex) {
        return new ResponseMessage(ex.getMessage());
    }

}
