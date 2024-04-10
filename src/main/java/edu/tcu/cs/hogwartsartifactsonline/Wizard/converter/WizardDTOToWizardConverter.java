package edu.tcu.cs.hogwartsartifactsonline.Wizard.converter;

import edu.tcu.cs.hogwartsartifactsonline.Wizard.Wizard;
import edu.tcu.cs.hogwartsartifactsonline.Wizard.WizardDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component

public class WizardDTOToWizardConverter implements Converter<WizardDTO, Wizard> {

    public Wizard convert(WizardDTO wizardDTO) {
        Wizard result = new Wizard();
        result.setId(wizardDTO.id());
        result.setName(wizardDTO.name());
        return result;
    }
}
