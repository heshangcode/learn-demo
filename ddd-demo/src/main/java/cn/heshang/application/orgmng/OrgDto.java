package cn.heshang.application.orgmng;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/6 08:57
 * version: 1.0
 */
@NoArgsConstructor
@Data
public class OrgDto {
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("createdBy")
    private Integer createdBy;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("lastUpdatedAt")
    private String lastUpdatedAt;
    @JsonProperty("lastUpdatedBy")
    private Integer lastUpdatedBy;
    @JsonProperty("leader")
    private Integer leader;
    @JsonProperty("name")
    private String name;
    @JsonProperty("orgType")
    private String orgType;
    @JsonProperty("status")
    private String status;
    @JsonProperty("superior")
    private Integer superior;
    @JsonProperty("tenant")
    private Integer tenant;
}
