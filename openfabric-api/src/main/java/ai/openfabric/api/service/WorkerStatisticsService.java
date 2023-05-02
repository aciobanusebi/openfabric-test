package ai.openfabric.api.service;

import ai.openfabric.api.dto.WorkerStatisticsDto;
import ai.openfabric.api.exception.WorkerNotFoundException;
import ai.openfabric.api.exception.WorkerNotRunningException;
import ai.openfabric.api.mapper.WorkerStatisticsMapper;
import ai.openfabric.api.mapper.WorkerStatsContainerStatsMapper;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.model.WorkerStatistics;
import ai.openfabric.api.repository.WorkerRepository;
import ai.openfabric.api.repository.WorkerStatisticsRepository;
import ai.openfabric.api.utils.DockerOps;
import com.github.dockerjava.api.model.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerStatisticsService {

    private final WorkerStatsContainerStatsMapper workerStatsContainerStatsMapper;
    private final WorkerStatisticsMapper workerStatisticsMapper;
    private final WorkerRepository workerRepository;

    private final WorkerStatisticsRepository workerStatisticsRepository;

    private final DockerOps dockerOps;


    public WorkerStatisticsDto getWorkerStatistics(String containerId) {
        Worker worker = workerRepository.findByContainerId(containerId).orElseThrow(() -> new WorkerNotFoundException(containerId));
        if (!worker.getState().equals("running")) {
            throw new WorkerNotRunningException(containerId);
        }
        WorkerStatistics existingWorkerStatistics = worker.getWorkerStatistics();
        Statistics statistics = dockerOps.getStatistics(containerId);
        WorkerStatistics workerStatistics = workerStatsContainerStatsMapper.containerToWorker(statistics);
        workerStatistics.setWorker(worker);
        if (existingWorkerStatistics != null) {
            workerStatistics.setId(existingWorkerStatistics.getId());
        }
        workerStatisticsRepository.save(workerStatistics);
        return workerStatisticsMapper.entityToDto(workerStatistics);
    }
}
