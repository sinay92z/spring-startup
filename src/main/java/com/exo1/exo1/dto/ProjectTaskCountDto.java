package com.exo1.exo1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTaskCountDto {
    private Long projectId;
    private String projectName;
    private Long taskCount;
}