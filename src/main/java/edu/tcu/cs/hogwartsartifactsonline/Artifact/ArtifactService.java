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
        if(foundArtifact.isPresent()) {
            return foundArtifact.get();
        } else {
            return null;
        }
    }

}
