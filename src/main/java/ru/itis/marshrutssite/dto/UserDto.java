package ru.itis.marshrutssite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.marshrutssite.models.User;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String state;
    private String token;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().name())
                .state(user.getState().name())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users
             ) {
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .role(user.getRole().name())
                    .state(user.getState().name())
                    .build();
            userDtos.add(userDto);
        }
        return userDtos;
    }
}