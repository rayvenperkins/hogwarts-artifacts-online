package edu.tcu.cs.hogwartsartifactsonline.Artifact;

import edu.tcu.cs.hogwartsartifactsonline.Artifact.converter.ArtifactDTOToArtifactConverter;
import edu.tcu.cs.hogwartsartifactsonline.Artifact.converter.ArtifactToArtifactDTOConverter;
import edu.tcu.cs.hogwartsartifactsonline.domain.Result;
import edu.tcu.cs.hogwartsartifactsonline.domain.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class ArtifactController {
    private final ArtifactService artifactService;
    private final ArtifactToArtifactDTOConverter artifactToArtifactDTOConverter;
    private final ArtifactDTOToArtifactConverter artifactDTOToArtifactConverter;

    public ArtifactController(ArtifactService artifactService, ArtifactToArtifactDTOConverter artifactToArtifactDTOConverter, ArtifactDTOToArtifactConverter artifactDTOToArtifactConverter) {
        this.artifactService = artifactService;
        this.artifactToArtifactDTOConverter = artifactToArtifactDTOConverter;
        this.artifactDTOToArtifactConverter = artifactDTOToArtifactConverter;
    }

    @GetMapping("/artifacts")
    public Result findAllArtifacts(Pageable pageable) {
        Page<Artifact> artifactPage = artifactService.findAll(pageable);
        Page<ArtifactDTO> artifactDTOS = artifactPage
                .map(this.artifactToArtifactDTOConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Find All Success", artifactDTOS);
    }

    @GetMapping("/artifacts/{artifactId}")
    public Result findArtifactById(@PathVariable String artifactId) {
        Artifact foundArtifact = artifactService.findArtifactById(artifactId);
        ArtifactDTO artifactDTO = artifactToArtifactDTOConverter.convert(foundArtifact);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", artifactDTO);

    }

    @PutMapping("/artifacts/{artifactId}")
    public Result updateArtifact(@PathVariable String artifactId, @RequestBody ArtifactDTO artifactDTO) {
        Artifact newArtifact = this.artifactDTOToArtifactConverter.convert(artifactDTO);
        Artifact updatedArtifact = this.artifactService.updateArtifact(artifactId, newArtifact);
        ArtifactDTO retval = this.artifactToArtifactDTOConverter.convert(updatedArtifact);
        return new Result(true, StatusCode.SUCCESS, "Update Artifact Success", retval);

    }

    @PostMapping("/artifacts/")
    public Result createArtifact(@RequestBody ArtifactDTO artifactDTO) {
        Artifact newArtifact = this.artifactDTOToArtifactConverter.convert(artifactDTO);
        Artifact savedArtifact = this.artifactService.save(newArtifact);
        ArtifactDTO retval = this.artifactToArtifactDTOConverter.convert(savedArtifact);
        return new Result(true, StatusCode.SUCCESS, "Create Artifact Success", retval);

    }

    @DeleteMapping("/artifacts/{artifactId}")
    public Result deleteArtifact(@PathVariable String artifactId) {
        this.artifactService.deleteArtifact(artifactId);

        return new Result(true, StatusCode.SUCCESS, "Delete Success", null);

    }

    @PostMapping("/search")
    public Result findArtifactsByCriteria(@RequestBody Map<String, String> searchCriteria, Pageable pageable) {
        Page<Artifact> artifactPage = this.artifactService.findByCriteria(searchCriteria, pageable);
        Page<ArtifactDTO> artifactDtoPage = artifactPage.map(this.artifactToArtifactDTOConverter::convert);
        return new Result(true, StatusCode.SUCCESS, "Search Success", artifactDtoPage);
    }
}
