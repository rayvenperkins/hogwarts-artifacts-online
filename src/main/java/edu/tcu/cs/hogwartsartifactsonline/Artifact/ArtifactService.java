package edu.tcu.cs.hogwartsartifactsonline.Artifact;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    @Autowired
    public ArtifactService(ArtifactRepository artifactRepository) {
        this.artifactRepository = artifactRepository;
    }

    public Page<Artifact> findAll(Pageable pageable) {
        return artifactRepository.findAll(pageable);
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

    public Page<Artifact> findByCriteria(Map<String, String> searchCriteria, Pageable pageable) {
        Specification<Artifact> spec = Specification.where(null);

        if (StringUtils.hasLength(searchCriteria.get("id"))) {
            spec = spec.and(ArtifactSpecs.hasId(searchCriteria.get("id")));
        }

        if (StringUtils.hasLength(searchCriteria.get("name"))) {
            spec = spec.and(ArtifactSpecs.containsName(searchCriteria.get("name")));
        }

        if (StringUtils.hasLength(searchCriteria.get("description"))) {
            spec = spec.and(ArtifactSpecs.containsDescription(searchCriteria.get("description")));
        }

        if (StringUtils.hasLength(searchCriteria.get("ownerName"))) {
            spec = spec.and(ArtifactSpecs.hasOwnerName(searchCriteria.get("ownerName")));
        }

        return this.artifactRepository.findAll(spec, pageable);
    }

}
