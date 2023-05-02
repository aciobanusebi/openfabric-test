package ai.openfabric.api.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
public class Worker extends Datable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    @Getter
    @Setter
    public String id;

    public String command;
    public String created;
    @Getter
    @Setter
    public String containerId;
    public String image;
    public String imageId;
    public String names;
    public String ports;
    public String labels;
    @Getter
    @Setter
    public String status;
    @Getter
    @Setter
    public String state;
    public String sizeRw;
    public String sizeRootFs;
    public String hostConfig;
    public String networkSettings;
    public String mounts;

    @OneToOne(mappedBy = "worker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter
    @Setter
    public WorkerStatistics workerStatistics;

}
