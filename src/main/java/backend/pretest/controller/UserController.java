package backend.pretest.controller;

import backend.pretest.entity.User;
import backend.pretest.model.ChangePasswordRequest;
import backend.pretest.model.LoginResponse;
import backend.pretest.model.UserUpdateRequest;
import backend.pretest.service.UserService;
import backend.pretest.service.token.TokenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final TokenService tokenService;

    @SneakyThrows
    @GetMapping("profile")
    public ResponseEntity<User> profile() {
        User user = tokenService.getUserByToken();
        return ResponseEntity.ok(user);
    }

    @SneakyThrows
    @PutMapping("update")
    public ResponseEntity<User> update(@RequestBody UserUpdateRequest request) {
        User user = tokenService.getUserByToken();
        User res = service.update(user,request.getEmail(),request.getNickName());
        return ResponseEntity.ok(res);
    }

    @SneakyThrows
    @PutMapping("changePassword")
    public ResponseEntity<LoginResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        User user = tokenService.getUserByToken();
        user = service.changePassword(user,request.getPasswordOld(),request.getPasswordNew());

        String tokenize = tokenService.tokenize(user);
        return ResponseEntity.ok(new LoginResponse(tokenize));
    }
}
