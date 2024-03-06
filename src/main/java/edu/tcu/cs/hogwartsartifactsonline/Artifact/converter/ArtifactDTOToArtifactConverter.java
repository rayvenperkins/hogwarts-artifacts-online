package edu.tcu.cs.hogwartsartifactsonline.Artifact.converter;

import edu.tcu.cs.hogwartsartifactsonline.Artifact.Artifact;
import edu.tcu.cs.hogwartsartifactsonline.Artifact.ArtifactDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ArtifactDTOToArtifactConverter implements Converter<ArtifactDTO, Artifact> {


    @Override
    public Artifact convert(ArtifactDTO source) {
        Artifact retval = new Artifact();
        retval.setId(source.id());
        retval.setName(source.name());
        retval.setDescription(source.description());
        retval.setImageUrl(source.imageUrl());
        return retval;
    }
}
