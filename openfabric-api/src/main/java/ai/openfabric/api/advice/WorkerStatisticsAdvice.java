package ai.openfabric.api.advice;

import ai.openfabric.api.exception.WorkerStatisticsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WorkerStatisticsAdvice {
    @ResponseBody
    @ExceptionHandler(WorkerStatisticsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseMessage workerStatisticsHandler(WorkerStatisticsException ex) {
        return new ResponseMessage(ex.getMessage());
    }


}
