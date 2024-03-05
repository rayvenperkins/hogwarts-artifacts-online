package edu.tcu.cs.hogwartsartifactsonline.domain;

import edu.tcu.cs.hogwartsartifactsonline.Artifact.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.Artifact.ArtifactRepository;
import edu.tcu.cs.hogwartsartifactsonline.Wizard.Wizard;
import edu.tcu.cs.hogwartsartifactsonline.Wizard.WizardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final ArtifactRepository artifactRepository;
    private final WizardRepository wizardRepository;

    public DBDataInitializer(ArtifactRepository artifactRepository, WizardRepository wizardRepository) {
        this.artifactRepository = artifactRepository;
        this.wizardRepository = wizardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Artifact a1 = new Artifact();
        a1.setId("12508");
        a1.setName("Deluminator");
        a1.setDescription("A Deluminator is a device invented by Albus Dum");
        a1.setImageUrl("ImageUrl");

        Artifact a2 = new Artifact();
        a2.setId("12509");
        a2.setName("Invisibility Cloak");
        a2.setDescription("An invisibility cloak is used to make the wearer invisible");
        a2.setImageUrl("ImageUrl");

        Artifact a3 = new Artifact();
        a3.setId("12510");
        a3.setName("The Marauder's Map");
        a3.setDescription("A magical map of Hogwarts created by Remus Lupin");
        a3.setImageUrl("ImageUrl");

        Artifact a4 = new Artifact();
        a4.setId("12511");
        a4.setName("Elder Wand");
        a4.setDescription("The Elder Wand, known throughout history as the");
        a4.setImageUrl("ImageUrl");

        Artifact a5 = new Artifact();
        a5.setId("12512");
        a5.setName("The Sword of Gryffindor");
        a5.setDescription("A goblin-made sword adorned with large rubies on");
        a5.setImageUrl("ImageUrl");

        Artifact a6 = new Artifact();
        a6.setId("12513");
        a6.setName("Resurrection Stone");
        a6.setDescription("The Resurrection Stone allows the holder to bring back deceasedlo");
        a6.setImageUrl("ImageUrl");


        Wizard w1 = new Wizard();
        w1.setId("1");
        w1.setName("Albus Dumbledore");
        w1.addArtifact(a1);
        w1.addArtifact(a3);

        Wizard w2 = new Wizard();
        w2.setId("2");
        w2.setName("Harry Potter");
        w2.addArtifact(a2);
        w2.addArtifact(a4);

        Wizard w3 = new Wizard();
        w3.setId("3");
        w3.setName("Neville Longbottom");
        w3.addArtifact(a5);


        wizardRepository.save(w1);
        wizardRepository.save(w2);
        wizardRepository.save(w3);
        artifactRepository.save(a6);
    }
}
