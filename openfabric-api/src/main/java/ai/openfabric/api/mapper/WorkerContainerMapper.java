package ai.openfabric.api.mapper;

import ai.openfabric.api.model.Worker;
import com.github.dockerjava.api.model.Container;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkerContainerMapper {
    @Mapping(source = "id", target = "containerId")
    Worker containerToWorker(Container container);

    @Mapping(source = "id", target = "containerId")
    List<Worker> containerToWorker(Iterable<Container> containers);

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
