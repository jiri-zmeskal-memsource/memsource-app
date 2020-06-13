package cz.denk.memsource.web.controller;

import cz.denk.memsource.service.MemsourceService;
import cz.denk.memsource.web.dto.ProjectDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectsController {

    private static final String PROJECTS_URI = "/api/projects";

    private MemsourceService memsourceService;

    public ProjectsController(MemsourceService memsourceService) {
        this.memsourceService = memsourceService;
    }

    @GetMapping(PROJECTS_URI)
    public List<ProjectDto> listProjects() {
        return this.memsourceService.listProjects();
    }

}
