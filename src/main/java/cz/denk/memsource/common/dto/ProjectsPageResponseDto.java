package cz.denk.memsource.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProjectsPageResponseDto {

    List<ProjectResponseDto> content;

    @JsonCreator
    public ProjectsPageResponseDto(@JsonProperty("content") List<ProjectResponseDto> content) {
        this.content = content;
    }

    public List<ProjectResponseDto> getContent() {
        return content;
    }
}
