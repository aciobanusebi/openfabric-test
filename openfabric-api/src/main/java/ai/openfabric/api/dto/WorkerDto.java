package ai.openfabric.api.dto;

import lombok.Data;

@Data
public class WorkerDto {
    public String command;
    public String created;
    public String containerId;
    public String image;
    public String imageId;
    public String names;
    public String ports;
    public String labels;
    public String status;
    public String state;
    public String sizeRw;
    public String sizeRootFs;
    public String hostConfig;
    public String networkSettings;
    public String mounts;
}
