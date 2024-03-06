package edu.tcu.cs.hogwartsartifactsonline.Artifact;

import edu.tcu.cs.hogwartsartifactsonline.Wizard.Wizard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ArtifactServiceTest {


    @Mock
    ArtifactRepository artifactRepository;

    @InjectMocks
    ArtifactService artifactService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
    }

    @Test
    void testFindArtifactByIdSuccess() {
        Artifact a = new Artifact();
        a.setId("123");
        a.setName("Invisibility Cloak");
        a.setDescription("An invisibility cloak is used to make the wearer invisible");
        a.setImageUrl("ImageUrl");

        Wizard w = new Wizard();
        w.setId("2");
        w.setName("Harry Potter");

        a.setOwner(w);
        given(artifactRepository.findById("123")).willReturn(Optional.of(a));

        Artifact returnedArtifact = artifactService.findArtifactById("123");

        assertThat(returnedArtifact.getId()).isEqualTo(a.getId());
        assertThat(returnedArtifact.getName()).isEqualTo(a.getName());
        assertThat(returnedArtifact.getDescription()).isEqualTo(a.getDescription());
        assertThat(returnedArtifact.getImageUrl()).isEqualTo(a.getImageUrl());
    }

    @Test
    void testDeleteArtifact() {
        String artifactId = "123";
        Mockito.doNothing().when(artifactRepository).deleteById(artifactId);

        artifactService.deleteArtifact(artifactId);

        verify(artifactRepository, times(1)).deleteById(artifactId);
    }

    @Test
    void testSaveArtifact() {
        Artifact newArtifact = new Artifact();
        newArtifact.setId("123");
        newArtifact.setName("old name");
        newArtifact.setDescription("old description");
        newArtifact.setImageUrl("image url");

        given(artifactRepository.save(newArtifact)).willReturn(newArtifact);

        Artifact returnedArtifact = artifactService.save(newArtifact);

        assertThat(returnedArtifact).isNotNull();
        assertThat(returnedArtifact.getId()).isEqualTo("123");
    }

    @Test
    void testUpdateArtifactSuccess() {
        String artifactId = "123";
        Artifact oldArtifact = new Artifact();
        oldArtifact.setId(artifactId);
        oldArtifact.setName("old name");
        oldArtifact.setDescription("old description");
        oldArtifact.setImageUrl("image url");


        Artifact updatedArtifact = new Artifact();
        updatedArtifact.setId(artifactId);
        updatedArtifact.setName("updated name");
        updatedArtifact.setDescription("updated description");
        updatedArtifact.setImageUrl("imageurl");

        given(artifactRepository.findById(artifactId)).willReturn(Optional.of(oldArtifact));
        given(artifactRepository.save(oldArtifact)).willReturn(updatedArtifact);

        Artifact result = artifactService.updateArtifact(artifactId, updatedArtifact);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(updatedArtifact.getName());
        assertThat(result.getDescription()).isEqualTo(updatedArtifact.getDescription());
        assertThat(result.getImageUrl()).isEqualTo(updatedArtifact.getImageUrl());
    }

    @Test
    void testUpdateArtifactNotFound() {
        String artifactId = "not_exist";
        Artifact updatedArtifact = new Artifact();
        updatedArtifact.setId(artifactId);
        updatedArtifact.setName("updated name");
        updatedArtifact.setDescription("updated description");
        updatedArtifact.setImageUrl("imageurl");
        given(artifactRepository.findById(artifactId)).willReturn(Optional.empty());

        Artifact result = artifactService.updateArtifact(artifactId, updatedArtifact);

        assertThat(result).isNull();
        verify(artifactRepository, never()).save(Mockito.any(Artifact.class));
    }
}