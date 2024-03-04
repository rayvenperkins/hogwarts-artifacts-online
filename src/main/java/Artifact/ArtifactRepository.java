package Artifact;

import org.springframework.data.jpa.repository.JpaRepository;
import domain.Artifact;

public interface ArtifactRepository extends JpaRepository<Artifact, String> {

}
