package edu.tcu.cs.hogwartsartifactsonline.Wizard;


import edu.tcu.cs.hogwartsartifactsonline.Artifact.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.Artifact.ArtifactRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WizardService {

    private final WizardRepository wizardRepository;
    private final ArtifactRepository artifactRepository;

    public WizardService(WizardRepository wizardRepository, ArtifactRepository artifactRepository) {
        this.wizardRepository = wizardRepository;
        this.artifactRepository = artifactRepository;
    }

    public Wizard createWizard(Wizard toBeSaved) {
        return wizardRepository.save(toBeSaved);
    }

    public List<Wizard> findAllWizards() {
        return wizardRepository.findAll();
    }

    public Wizard findWizardById(String id) {
        Optional<Wizard> optionalWizard = wizardRepository.findById(id);
        if(optionalWizard.isPresent()) {
            return optionalWizard.get();
        } else {
            return null;
        }
    }

    public Boolean deleteWizard(String id) {
        Optional<Wizard> optionalWizard = wizardRepository.findById(id);
        if(optionalWizard.isPresent()) {
            //need to remove wizard's owned artifacts here
            wizardRepository.delete(optionalWizard.get());
            return true;
        } else {
            return false;
        }
    }

    public Wizard updateWizard(String id, Wizard newInfoWizard) {
        Optional<Wizard> optionalWizard = wizardRepository.findById(id);
        if(optionalWizard.isPresent()) {
            Wizard updatedWizard = optionalWizard.get();
            updatedWizard.setId(newInfoWizard.getId());
            updatedWizard.setName(newInfoWizard.getName());
            return wizardRepository.save(updatedWizard);
        } else {
            return null;
        }
    }


    public void assignArtifactToWizard(String wizardId, String artifactId) {
        Wizard wizard = wizardRepository.findById(wizardId).orElseThrow(() -> new RuntimeException("Wizard not found"));
        Artifact artifact = artifactRepository.findById(artifactId).orElseThrow(() -> new RuntimeException("Artifact not found"));

        wizard.addArtifact(artifact);

        wizardRepository.save(wizard);
    }
}
