package uz.developers.auth.mapper;

import org.springframework.stereotype.Component;
import uz.developers.auth.dto.UsersDto;
import uz.developers.auth.entity.Users;

@Component
public class UsersMapper {

    public Users toEntity(UsersDto usersDto){
        return new Users(
                usersDto.getId(),
                usersDto.getFirst_name(),
                usersDto.getLast_name(),
                usersDto.getPhone_number(),
                usersDto.getEmail(),
                usersDto.getPassword(),
                true
        );
    }

    public UsersDto toDto(Users users){
        return new UsersDto(
                users.getId(),
                users.getFirst_name(),
                users.getLast_name(),
                users.getPhone_number(),
                users.getEmail(),
                users.getPassword()
        );
    }
}
