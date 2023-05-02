package ai.openfabric.api.utils;

import ai.openfabric.api.exception.WorkerStatisticsException;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Statistics;
import com.github.dockerjava.core.InvocationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class DockerOps {
    private final DockerClient dockerClient;

    public void init() {
        try {
            dockerClient.pullImageCmd("hello-world:linux")
                    .exec(new PullImageResultCallback())
                    .awaitCompletion(5, TimeUnit.MINUTES);
            dockerClient.pullImageCmd("mongo:5.0.17")
                    .exec(new PullImageResultCallback())
                    .awaitCompletion(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("hello-world:linux").exec();
        dockerClient.createContainerCmd("mongo:5.0.17").exec();
    }

    public List<Container> getContainers() {
        List<Container> containers = dockerClient.listContainersCmd()
                .withShowSize(true)
                .withShowAll(true)
                .exec();
        return containers;
    }

    public Container getContainerByContainerId(String id) {
        List<Container> containers = dockerClient.listContainersCmd()
                .withShowSize(true)
                .withShowAll(true)
                .withIdFilter(Collections.singleton(id))
                .exec();
        return containers.get(0);
    }

    public void start(String containedId) {
        dockerClient.startContainerCmd(containedId).exec();
    }

    public void stop(String containedId) {
        dockerClient.stopContainerCmd(containedId).exec();
    }

    public Statistics getStatistics(String containerId) {
        // after https://github.com/docker-java/docker-java/issues/1206#issuecomment-510789440
        InvocationBuilder.AsyncResultCallback<Statistics> callback = new InvocationBuilder.AsyncResultCallback<>();
        dockerClient.statsCmd(containerId).exec(callback);
        Statistics stats;
        try {
            stats = callback.awaitResult();
            callback.close();
        } catch (RuntimeException | IOException e) {
            // you may want to throw an exception here
            throw new WorkerStatisticsException(containerId);
        }
        if (stats == null) {
            throw new WorkerStatisticsException(containerId);
        }
        return stats; // this may be null or invalid if the container has terminated
    }
}
