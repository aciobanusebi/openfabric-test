package ai.openfabric.api.service;

import ai.openfabric.api.dto.WorkerDto;
import ai.openfabric.api.dto.WorkerResponse;
import ai.openfabric.api.exception.WorkerNotFoundException;
import ai.openfabric.api.exception.WorkerNotRunningException;
import ai.openfabric.api.exception.WorkerRunningException;
import ai.openfabric.api.mapper.WorkerMapper;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.repository.WorkerRepository;
import ai.openfabric.api.utils.DockerOps;
import com.github.dockerjava.api.model.Container;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerMapper workerMapper;
    private final WorkerRepository workerRepository;

    private final DockerOps dockerOps;

    public WorkerResponse getAllWorkers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Worker> workers = workerRepository.findAll(pageable);
        List<Worker> listOfWorkers = workers.getContent();
        List<WorkerDto> content = workerMapper.entityToDto(listOfWorkers);

        WorkerResponse workerResponse = new WorkerResponse();
        workerResponse.setContent(content);
        workerResponse.setPageNo(workers.getNumber());
        workerResponse.setPageSize(workers.getSize());
        workerResponse.setTotalElements(workers.getTotalElements());
        workerResponse.setTotalPages(workers.getTotalPages());
        workerResponse.setLast(workers.isLast());

        return workerResponse;
    }

    public void updateWorkerFromContainer(Worker worker) {
        Container container = dockerOps.getContainerByContainerId(worker.getContainerId());
        worker.setState(container.getState());
        worker.setStatus(container.getStatus());
        workerRepository.save(worker);
    }

    public void startWorker(String containerId) {
        Worker worker = workerRepository.findByContainerId(containerId).orElseThrow(() -> new WorkerNotFoundException(containerId));
        if (worker.getState().equals("running")) {
            throw new WorkerRunningException(containerId);
        }
        dockerOps.start(containerId);

        updateWorkerFromContainer(worker);
    }

    public void stopWorker(String containerId) {
        Worker worker = workerRepository.findByContainerId(containerId).orElseThrow(() -> new WorkerNotFoundException(containerId));
        if (!worker.getState().equals("running")) {
            throw new WorkerNotRunningException(containerId);
        }
        dockerOps.stop(containerId);

        updateWorkerFromContainer(worker);
    }

    public WorkerDto getWorker(String containerId) {
        Worker worker = workerRepository.findByContainerId(containerId).orElseThrow(() -> new WorkerNotFoundException(containerId));
        return workerMapper.entityToDto(worker);
    }
}
