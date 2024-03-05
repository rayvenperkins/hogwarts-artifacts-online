package edu.tcu.cs.hogwartsartifactsonline.Artifact;

import edu.tcu.cs.hogwartsartifactsonline.domain.Result;
import edu.tcu.cs.hogwartsartifactsonline.domain.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ArtifactController {
    private final ArtifactService artifactService;
    public ArtifactController(ArtifactService artifactService) {
        this.artifactService = artifactService;
    }


    @GetMapping("/artifacts")
    public Result findAllArtifacts() {
        List<Artifact> artifacts = artifactService.findAll();
        return new Result(true, StatusCode.SUCCESS, "Find All Success", artifacts);
    }

    @GetMapping("/artifacts/{artifactId}")
    public Result findArtifactById(@PathVariable String artifactId) {
        Artifact foundArtifact = artifactService.findArtifactById(artifactId);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", null);

    }

    @PutMapping("/artifacts/{artifactId}")
    public Result updateArtifact(@PathVariable String artifactId) {
        return new Result(true, StatusCode.SUCCESS, "Find One Success", null);

    }

    @PostMapping("/artifacts/{artifactId}")
    public Result createArtifact(@PathVariable String artifactId) {
        return new Result(true, StatusCode.SUCCESS, "Find One Success", null);

    }

    @DeleteMapping("/artifacts/{artifactId}")
    public Result deleteArtifact(@PathVariable String artifactId) {
        return new Result(true, StatusCode.SUCCESS, "Find One Success", null);

    }
}
