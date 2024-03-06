package edu.tcu.cs.hogwartsartifactsonline.Artifact.converter;

import edu.tcu.cs.hogwartsartifactsonline.Artifact.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.Artifact.ArtifactDTO;
import edu.tcu.cs.hogwartsartifactsonline.Wizard.WizardToWizardDTOConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArtifactToArtifactDTOConverter implements Converter<Artifact, ArtifactDTO> {

    private final WizardToWizardDTOConverter wizardToWizardDTOConverter;

    public ArtifactToArtifactDTOConverter(WizardToWizardDTOConverter wizardToWizardDTOConverter) {
        this.wizardToWizardDTOConverter = wizardToWizardDTOConverter;
    }

    @Override
    public ArtifactDTO convert(Artifact source) {
        return new ArtifactDTO(source.getId(),
                source.getName(),
                source.getDescription(),
                source.getImageUrl(),
                source.getOwner() != null
                        ? wizardToWizardDTOConverter.convert(source.getOwner())
                        : null);
    }
}
