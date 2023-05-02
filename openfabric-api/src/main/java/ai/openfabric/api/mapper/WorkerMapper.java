package ai.openfabric.api.mapper;

import ai.openfabric.api.dto.WorkerDto;
import ai.openfabric.api.model.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkerMapper {
    WorkerDto entityToDto(Worker worker);

    List<WorkerDto> entityToDto(Iterable<Worker> workers);

    Worker dtoToEntity(WorkerDto workerDto);

    List<Worker> dtoToEntity(Iterable<WorkerDto> workerDtos);
}
