package com.qxy.elder.api.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryMissionDto {
    private Long publishUserId;
    private Long accomplishUserId;
    private List<String> tags;
    private Integer page;
    private Integer pageSize;
}
