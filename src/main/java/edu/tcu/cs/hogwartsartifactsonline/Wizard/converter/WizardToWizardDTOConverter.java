package edu.tcu.cs.hogwartsartifactsonline.Wizard.converter;



import edu.tcu.cs.hogwartsartifactsonline.Wizard.Wizard;
import edu.tcu.cs.hogwartsartifactsonline.Wizard.WizardDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WizardToWizardDTOConverter implements Converter<Wizard, WizardDTO> {
    @Override
    public WizardDTO convert(Wizard source) {
        return new WizardDTO(source.getId(),
                source.getName(),
                source.getArtifacts().size());

    }
}
