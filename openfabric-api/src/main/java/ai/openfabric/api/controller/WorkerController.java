package ai.openfabric.api.controller;

import ai.openfabric.api.dto.WorkerDto;
import ai.openfabric.api.dto.WorkerResponse;
import ai.openfabric.api.service.WorkerService;
import ai.openfabric.api.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${node.api.path}/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping(path = "/hello")
    public @ResponseBody String hello(@RequestBody String name) {
        return "Hello!" + name;
    }

    @GetMapping
    public WorkerResponse getAllWorkers(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        // after: https://www.javaguides.net/2021/10/spring-boot-pagination-and-sorting-rest-api.html
        return workerService.getAllWorkers(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping(path = "/{containerId}")
    public WorkerDto getWorker(@PathVariable String containerId) {
        return workerService.getWorker(containerId);
    }

    @PutMapping(path = "/{containerId}/start")
    public void startWorker(@PathVariable String containerId) {
        workerService.startWorker(containerId);
    }

    @PutMapping(path = "/{containerId}/stop")
    public void stopWorker(@PathVariable String containerId) {
        workerService.stopWorker(containerId);
    }

}
