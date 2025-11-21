package br.com.arthdroid1.mapper;

import br.com.arthdroid1.dtos.UserDTO;
import br.com.arthdroid1.models.User;

public class UserMapper {
	
	 public static UserDTO toDTO(User user) {
	        return new UserDTO(user.getId(), user.getName(), user.getEmail());
	    }

	    public static User toEntity(UserDTO dto, String password) {
	        return new User(dto.getName(), dto.getEmail(), password);
	    }

}
