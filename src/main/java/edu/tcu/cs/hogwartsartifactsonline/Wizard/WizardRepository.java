package edu.tcu.cs.hogwartsartifactsonline.Wizard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository extends JpaRepository<Wizard, String> {

}
