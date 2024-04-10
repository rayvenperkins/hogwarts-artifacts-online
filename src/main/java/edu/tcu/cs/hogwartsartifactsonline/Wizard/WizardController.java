package edu.tcu.cs.hogwartsartifactsonline.Wizard;


import edu.tcu.cs.hogwartsartifactsonline.Wizard.converter.WizardDTOToWizardConverter;
import edu.tcu.cs.hogwartsartifactsonline.Wizard.converter.WizardToWizardDTOConverter;
import edu.tcu.cs.hogwartsartifactsonline.domain.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WizardController {
    private final WizardService wizardService;
    private final WizardToWizardDTOConverter wizardToWizardDTOConverter;
    private final WizardDTOToWizardConverter wizardDTOToWizardConverter;

    public WizardController(WizardService wizardService, WizardToWizardDTOConverter wizardToWizardDTOConverter, WizardDTOToWizardConverter wizardDTOToWizardConverter) {
        this.wizardService = wizardService;
        this.wizardToWizardDTOConverter = wizardToWizardDTOConverter;
        this.wizardDTOToWizardConverter = wizardDTOToWizardConverter;
    }


    @PostMapping("/wizard/")
    public Result createWizard(@RequestBody WizardDTO wizardDTO) {
        Wizard toBeSaved = wizardDTOToWizardConverter.convert(wizardDTO);
        Wizard savedWizard = wizardService.createWizard(toBeSaved);
        WizardDTO result = wizardToWizardDTOConverter.convert(savedWizard);
        return new Result(true, 200, "Wizard created successfully", result);
    }

    @GetMapping("/wizards")
    public Result findAllWizards() {
        List<Wizard> result = wizardService.findAllWizards();
        return new Result(true, 200, "Find All Success", result);
    }

    @GetMapping("/wizard/{id}")
    public Result findWizardById(@PathVariable String id) {
        Wizard foundWizard = wizardService.findWizardById(id);
        if(foundWizard != null) {
            WizardDTO result = wizardToWizardDTOConverter.convert(foundWizard);
            return new Result(true, 200, "Find One Success", result);
        } else {
            return new Result(false, 400, "Find One Failure", null);
        }
    }

    @DeleteMapping("/wizard/{id}")
    public Result deleteWizard(@PathVariable String id) {
        Boolean successBool = wizardService.deleteWizard(id);
        if(successBool) {
            return new Result(true, 200, "Deleted wizard successfully", null);
        } else {
            return new Result(false, 400, "Delete wizard Failure", null);
        }
    }


    @PutMapping("/wizard/{id}")
    public Result updateWizard(@PathVariable String id, @RequestBody WizardDTO wizardDTO) {
        Wizard newInfoWizard = wizardDTOToWizardConverter.convert(wizardDTO);
        Wizard updatedWizard = wizardService.updateWizard(id, newInfoWizard);
        if(updatedWizard != null) {
            WizardDTO result = wizardToWizardDTOConverter.convert(updatedWizard);
            return new Result(true, 200, "Updated wizard successfully", result);
        } else {
            return new Result(false, 400, "Update wizard Failure", null);
        }
    }



}
