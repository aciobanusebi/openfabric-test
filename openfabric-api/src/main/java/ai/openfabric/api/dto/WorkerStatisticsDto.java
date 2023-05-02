package ai.openfabric.api.dto;

import lombok.Data;

@Data
public class WorkerStatisticsDto {
    public String read;
    public String preread;
    public String networks;
    public String memoryStats;
    public String blkioStats;
    public String cpuStats;
    public String numProcs;
    public String preCpuStats;
}
