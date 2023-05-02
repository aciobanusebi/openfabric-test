package ai.openfabric.api.scheduler;

import ai.openfabric.api.mapper.WorkerContainerMapper;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.repository.WorkerRepository;
import ai.openfabric.api.utils.DockerOps;
import com.github.dockerjava.api.model.Container;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final DockerOps dockerOps;

    private final WorkerContainerMapper workerContainerMapper;

    private final WorkerRepository workerRepository;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void updateDatabase() {
        List<Container> containers = dockerOps.getContainers();
        if (containers.size() == 0) {
            // First start
            dockerOps.init();
            containers = dockerOps.getContainers();
        }
        List<Worker> workers = workerContainerMapper.containerToWorker(containers);
        workerRepository.deleteAll();
        workerRepository.saveAll(workers);
        log.info("Scheduled update for database: {}", dateFormat.format(new Date()));
    }
}
