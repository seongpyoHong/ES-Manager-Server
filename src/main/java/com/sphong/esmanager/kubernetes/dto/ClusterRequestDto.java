package com.sphong.esmanager.kubernetes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ClusterRequestDto {
    @JsonIgnore
    private String projectId;
    private String clusterName;
    private String hotNodeSpec;
    private String warmNodeSpec;
    private String defaultNodeSpec;
    private String nodeRegion;

    public Map<String, String> getMembers() {
        Map<String, String> members  = new HashMap<>();
        members.put("project_id", this.projectId);
        members.put("cluster_name", this.clusterName);
        members.put("ssd_node_spec", this.hotNodeSpec);
        members.put("hdd_node_spec", this.warmNodeSpec);
        members.put("default_node_spec", this.defaultNodeSpec);
        members.put("node_region", this.nodeRegion);
        return members;
    }
}
