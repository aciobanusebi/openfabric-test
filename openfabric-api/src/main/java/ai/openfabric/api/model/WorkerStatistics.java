package ai.openfabric.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class WorkerStatistics extends Datable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    @Getter
    @Setter
    public String id;

    public String read;
    public String preread;
    public String networks;
    public String memoryStats;
    public String blkioStats;
    public String cpuStats;
    public String numProcs;
    public String preCpuStats;

    @OneToOne
    @Getter
    @Setter
    public Worker worker;
}
