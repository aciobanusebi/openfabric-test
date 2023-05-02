package ai.openfabric.api.mapper;

import ai.openfabric.api.model.WorkerStatistics;
import com.github.dockerjava.api.model.Statistics;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkerStatsContainerStatsMapper {
    WorkerStatistics containerToWorker(Statistics statistics);

    List<WorkerStatistics> containerToWorker(Iterable<Statistics> listStatistics);

    default String map(Object value) {
        if (value == null) {
            return null;
        }
        String result = value.toString();
        return result.substring(0, Math.min(255, result.length()));
    }

    default String map(Object[] value) {
        if (value == null) {
            return null;
        }
        String result = Arrays.toString(value);
        return result.substring(0, Math.min(255, result.length()));
    }
}
