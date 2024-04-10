package edu.tcu.cs.hogwartsartifactsonline.HogwartsUser.converter;

import edu.tcu.cs.hogwartsartifactsonline.HogwartsUser.HogwartsUser;
import edu.tcu.cs.hogwartsartifactsonline.HogwartsUser.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, HogwartsUser> {

    @Override
    public HogwartsUser convert(UserDto source) {
        HogwartsUser hogwartsUser = new HogwartsUser();
        hogwartsUser.setUsername(source.username());
        hogwartsUser.setEnabled(source.enabled());
        hogwartsUser.setRoles(source.roles());
        return hogwartsUser;
    }

}
