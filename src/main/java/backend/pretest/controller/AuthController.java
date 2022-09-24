package backend.pretest.controller;

import backend.pretest.entity.Content;
import backend.pretest.entity.User;
import backend.pretest.model.*;
import backend.pretest.service.ContentService;
import backend.pretest.service.UserService;
import backend.pretest.service.token.TokenService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final ContentService contentService;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(ContentService contentService, UserService userService, TokenService tokenService) {
        this.contentService = contentService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("contents")
    public ResponseEntity<List<Content>> contents() {
        List<Content> res = contentService.findAll();
        return ResponseEntity.ok(res);
    }

    @SneakyThrows
    @GetMapping("content")
    public ResponseEntity<Content> contents(@RequestParam(name = "id") Integer id) {
        Content res = contentService.findById(id);
        return ResponseEntity.ok(res);
    }

    @SneakyThrows
    @PostMapping("content")
    public ResponseEntity<OkResponse> createContent(@RequestBody ContentRequest request) {
        request.verify();
        contentService.createContent(request.getTitle(), request.getDescription());
        return ResponseEntity.ok(new OkResponse("บันทึกข้อมูลสำเร็จ"));
    }


    @SneakyThrows
    @PutMapping("content")
    public ResponseEntity<OkResponse> updateContent(@RequestParam(name = "id") Integer id, @RequestBody ContentRequest request) {
        request.verify();
        contentService.updateContent(id, request.getTitle(), request.getDescription());
        return ResponseEntity.ok(new OkResponse("แก้ไขข้อมูลสำเร็จ"));
    }
    @SneakyThrows
    @DeleteMapping("content")
    public ResponseEntity<OkResponse> deleteContent(@RequestParam(name = "id") Integer id) {

        contentService.deleteContent(id);
        return ResponseEntity.ok(new OkResponse("ลบรายการสำเร็จ"));
    }
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = userService.login(request.getUserName(), request.getPassword());

        String token = tokenService.tokenize(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @SneakyThrows
    @PostMapping("register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request) {
        request.verify();
        User user = userService.createUser(request.getUserName(), request.getEmail(), request.getPassword(), request.getNickName());

        String token = tokenService.tokenize(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
