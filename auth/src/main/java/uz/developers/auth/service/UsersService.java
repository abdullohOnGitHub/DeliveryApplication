package uz.developers.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.developers.auth.dto.ResponseDto;
import uz.developers.auth.dto.UsersDto;
import uz.developers.auth.entity.Users;
import uz.developers.auth.mapper.UsersMapper;
import uz.developers.auth.repo.UsersRepo;
import uz.developers.auth.util.PasswordUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepo usersRepo;
    private final UsersMapper usersMapper;
    private final PasswordUtils passwordUtils;

    public ResponseDto registerUser(UsersDto usersDto){
        Users users = usersMapper.toEntity(usersDto);

        if (!usersRepo.existsByEmailAndIsActive(users.getEmail(),true)){
            users.setPassword(passwordUtils.encodePassword(users.getPassword()));
            return ResponseDto.getSuccess(usersMapper.toDto(usersRepo.save(users)));
        }else{
            return new ResponseDto(-1,"Email already exists",null);
        }
    }

    public ResponseDto loginUser(String email, String password){
        Optional<Users> optionalUsers = usersRepo.findByEmailAndIsActive(email, true);
        if (optionalUsers.isPresent()){
            Users user = optionalUsers.get();
            if (passwordUtils.match(password,user.getPassword())){
                return ResponseDto.getSuccess(usersMapper.toDto(user));
            }else{
                return new ResponseDto(-1,"Email or password is incorrect",null);
            }
        }else {
            return ResponseDto.UserNotFound();
        }
    }

    public ResponseDto updateUser(UsersDto usersDto){
        try {
            Optional<Users> optionalUsers = usersRepo.findByIdAndIsActive(usersDto.getId(),true);
            System.out.println(usersDto);
            if (optionalUsers.isPresent()){
                return ResponseDto.getSuccess(usersMapper.toDto(usersRepo.save(usersMapper.toEntity(usersDto))));
            }else{
                return ResponseDto.UserNotFound();
            }
        }catch (Exception e){
            return new ResponseDto(-1,"Id is null",null);
        }
    }

    public ResponseDto getAllUsers(){
        List<Users> users = usersRepo.findAllByIsActive(true);
        List<UsersDto> usersList = new LinkedList<>();
        for (Users user : users) {
            usersList.add(usersMapper.toDto(user));
        }
        return ResponseDto.getSuccess(usersList);
    }

    public ResponseDto getById(Integer id){
        Optional<Users> users = usersRepo.findByIdAndIsActive(id,true);
        return users.map(value -> ResponseDto.getSuccess(usersMapper.toDto(value))).orElseGet(ResponseDto::UserNotFound);
    }

    public ResponseDto deleteUser(Integer id){
        Optional<Users> users = usersRepo.findByIdAndIsActive(id,true);
        if (users.isPresent()){
            // Agar usha user mavjud bo'lsa uni o'chirmasdan, is_active ni false qilib qo'yamiz.
            users.get().setIsActive(false);
            usersRepo.save(users.get());
            return new ResponseDto(0, "User deleted",null);
        }else{
            return ResponseDto.UserNotFound();
        }
    }
}
