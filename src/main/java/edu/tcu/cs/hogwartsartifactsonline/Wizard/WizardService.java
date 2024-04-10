package edu.tcu.cs.hogwartsartifactsonline.Wizard;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WizardService {

    private final WizardRepository wizardRepository;

    public WizardService(WizardRepository wizardRepository) {
        this.wizardRepository = wizardRepository;
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
}
