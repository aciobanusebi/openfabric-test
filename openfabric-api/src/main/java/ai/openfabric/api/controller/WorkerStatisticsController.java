package ai.openfabric.api.controller;

import ai.openfabric.api.dto.WorkerStatisticsDto;
import ai.openfabric.api.service.WorkerStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${node.api.path}/workerStatistics")
@RequiredArgsConstructor
public class WorkerStatisticsController {

    private final WorkerStatisticsService workerStatisticsService;


    @GetMapping(path = "/{containerId}")
    public WorkerStatisticsDto getWorkerStatistics(@PathVariable String containerId) {
        return workerStatisticsService.getWorkerStatistics(containerId);
    }


}
