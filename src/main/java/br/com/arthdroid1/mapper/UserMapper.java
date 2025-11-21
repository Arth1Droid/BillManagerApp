package br.com.arthdroid1.mapper;

import br.com.arthdroid1.dtos.UserRequestDTO;
import br.com.arthdroid1.dtos.UserResponseDTO;
import br.com.arthdroid1.models.User;

public class UserMapper {
	
    public static User toEntity(UserRequestDTO request) {
        return new User(request.getName(), request.getEmail(), request.getPassword());
    }

    public static UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

}
