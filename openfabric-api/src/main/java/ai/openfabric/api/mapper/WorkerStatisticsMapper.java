package ai.openfabric.api.mapper;

import ai.openfabric.api.dto.WorkerStatisticsDto;
import ai.openfabric.api.model.WorkerStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkerStatisticsMapper {
    WorkerStatisticsDto entityToDto(WorkerStatistics workerStatistics);

    List<WorkerStatisticsDto> entityToDto(Iterable<WorkerStatistics> listWorkerStatistics);

    WorkerStatistics dtoToEntity(WorkerStatisticsDto workerStatisticsDto);

    List<WorkerStatistics> dtoToEntity(Iterable<WorkerStatisticsDto> workerStatisticsDtos);
}
