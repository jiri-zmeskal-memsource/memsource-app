package cz.denk.memsource.common.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProjectResponseDto {

    private String name;
    private String status;
    private String sourceLang;
    private List<String> targetLangs;

    @JsonCreator
    public ProjectResponseDto(@JsonProperty("name") String name,
                              @JsonProperty("status") String status,
                              @JsonProperty("sourceLang") String sourceLang,
                              @JsonProperty("targetLangs") List<String> targetLangs) {
        this.name = name;
        this.status = status;
        this.sourceLang = sourceLang;
        this.targetLangs = targetLangs;
    }

    public String getStatus() {
        return status;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public List<String> getTargetLangs() {
        return targetLangs;
    }

    public String getName() {
        return name;
    }
}
