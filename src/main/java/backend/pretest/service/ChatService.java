package backend.pretest.service;

import backend.pretest.entity.User;
import backend.pretest.model.ChatMessage;
import backend.pretest.model.MessageRequest;
import backend.pretest.service.token.TokenService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class ChatService {

    private final SimpMessagingTemplate template;
    private final TokenService tokenService;

    public ChatService(SimpMessagingTemplate template, TokenService tokenService) {
        this.template = template;
        this.tokenService = tokenService;
    }

    public void post(MessageRequest request) {

        User user = tokenService.getUserByToken();

        final String destination = "/topic/chat";

        ChatMessage payload = new ChatMessage();
        payload.setFrom(user.getNickName());
        payload.setMessage(request.getMessage());

        template.convertAndSend(destination, payload);

    }
}
