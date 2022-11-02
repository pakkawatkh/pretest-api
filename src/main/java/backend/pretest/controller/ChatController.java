package backend.pretest.controller;

import backend.pretest.model.MessageRequest;
import backend.pretest.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {

    private final ChatService service;

    @PostMapping("/message")
    public ResponseEntity<Void> post(@RequestBody MessageRequest request) {
        service.post(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
