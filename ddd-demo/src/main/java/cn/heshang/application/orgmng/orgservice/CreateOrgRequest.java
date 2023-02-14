package cn.heshang.application.orgmng.orgservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/1/17 09:09
 * version: 1.0
 */
@NoArgsConstructor
@Data
public class CreateOrgRequest {
    @JsonProperty("leader")
    private Integer leader;
    @JsonProperty("name")
    private String name;
    @JsonProperty("orgType")
    private String orgType;
    @JsonProperty("superior")
    private Integer superior;
    @JsonProperty("tenant")
    private Integer tenant;
}
