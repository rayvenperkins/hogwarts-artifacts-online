package edu.tcu.cs.hogwartsartifactsonline.Artifact;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    @Autowired
    public ArtifactService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    public List<Artifact> findAll() {
        return artifactRepository.findAll();
    }


    public Artifact findArtifactById(String artifactId) {
        Optional<Artifact> foundArtifact = artifactRepository.findById(artifactId);
        return foundArtifact.orElse(null);
    }

    public Artifact save(Artifact newArtifact) {
        return artifactRepository.save(newArtifact);
    }

    public void deleteArtifact(String artifactId) {
        this.artifactRepository.deleteById(artifactId);
    }

    public Artifact updateArtifact(String artifactId, Artifact updatedArtifact) {
        //get the old artifact
        Optional<Artifact> oldArtifactOptional = this.artifactRepository.findById(artifactId);
        if(oldArtifactOptional.isPresent()) {
            Artifact oldArtifact = oldArtifactOptional.get();
            oldArtifact.setName(updatedArtifact.getName());
            oldArtifact.setDescription(updatedArtifact.getDescription());
            oldArtifact.setImageUrl(updatedArtifact.getImageUrl());

            Artifact update = this.artifactRepository.save(oldArtifact);
            return update;
        } else {
            return null;
        }
    }

    public String summarize(List<ArtifactDTO> artifactDtos) {
        return null;
    }

}
