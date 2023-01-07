package uz.developers.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.developers.auth.dto.ResponseDto;
import uz.developers.auth.dto.UsersDto;
import uz.developers.auth.service.UsersService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/all")
    public ResponseDto getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseDto getUserById(@PathVariable Integer id){
        return usersService.getById(id);
    }

    @PostMapping("/register")
    public ResponseDto registerUser(@RequestBody UsersDto usersDto){
        return usersService.registerUser(usersDto);
    }

    @PostMapping("/login")
    public ResponseDto loginUser(@RequestParam String email, @RequestParam String password){
        return usersService.loginUser(email,password);
    }

    @PutMapping("/updateUser")
    public ResponseDto updateUser(@RequestBody UsersDto usersDto){
        return usersService.updateUser(usersDto);
    }

    @DeleteMapping("/deleteUser")
    public ResponseDto deleteUser(@RequestParam Integer id){
        return usersService.deleteUser(id);
    }



}
