package cz.denk.memsource.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NONE;

@JsonTypeInfo(use = NONE)
public class ProjectDto {

    private String name;
    private String status;
    private String sourceLang;
    private List<String> targetLangs;


    @JsonCreator
    public ProjectDto(@JsonProperty("name") String name,
                      @JsonProperty("status") String status,
                      @JsonProperty("sourceLang") String sourceLang,
                      @JsonProperty("targetLangs") List<String> targetLangs) {
        this.name = name;
        this.status = status;
        this.sourceLang = sourceLang;
        this.targetLangs = targetLangs;
    }

    public String getName() {
        return name;
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
}

