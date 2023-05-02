package ai.openfabric.api.repository;

import ai.openfabric.api.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, String> {
    Optional<Worker> findByContainerId(String containerId);
}
