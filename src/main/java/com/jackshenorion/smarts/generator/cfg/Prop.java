package com.jackshenorion.smarts.generator.cfg;

import com.jackshenorion.smarts.generator.EnxtCfgGenerator;

public class Prop {
    public String partitionName;
    public String accessId;
    public String partitionId;

    public Prop(String partitionName, String accessId, String partitionId) {
        this.partitionName = partitionName;
        this.accessId = accessId;
        this.partitionId = partitionId;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public Prop setPartitionName(String partitionName) {
        this.partitionName = partitionName;
        return this;
    }

    public String getAccessId() {
        return accessId;
    }

    public Prop setAccessId(String accessId) {
        this.accessId = accessId;
        return this;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public Prop setPartitionId(String partitionId) {
        this.partitionId = partitionId;
        return this;
    }
}
