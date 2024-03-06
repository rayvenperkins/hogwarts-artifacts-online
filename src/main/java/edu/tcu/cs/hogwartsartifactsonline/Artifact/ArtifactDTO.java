package edu.tcu.cs.hogwartsartifactsonline.Artifact;

import edu.tcu.cs.hogwartsartifactsonline.Wizard.WizardDTO;

public record ArtifactDTO(String id, String name, String description, String imageUrl, WizardDTO wizard) {
}
