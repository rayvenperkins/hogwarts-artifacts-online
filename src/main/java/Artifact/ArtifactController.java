package Artifact;

import domain.Result;
import domain.StatusCode;
import domain.Artifact;
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
    public Result findArtifactById(@PathVariable Integer artifactId) {
        return new Result(true, StatusCode.SUCCESS, "Find One Success", null);

    }

    @PutMapping("/artifacts/{artifactId}")
    public Result updateArtifact(@PathVariable Integer artifactId) {
        return new Result(true, StatusCode.SUCCESS, "Find One Success", null);

    }

    @PostMapping("/artifacts/{artifactId}")
    public Result createArtifact(@PathVariable Integer artifactId) {
        return new Result(true, StatusCode.SUCCESS, "Find One Success", null);

    }

    @DeleteMapping("/artifacts/{artifactId}")
    public Result deleteArtifact(@PathVariable Integer artifactId) {
        return new Result(true, StatusCode.SUCCESS, "Find One Success", null);

    }
}
