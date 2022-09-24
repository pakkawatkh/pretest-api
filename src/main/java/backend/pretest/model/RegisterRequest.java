package backend.pretest.model;

import backend.pretest.exception.BaseBadRequestException;
import lombok.Data;

@Data
public class RegisterRequest {
    private String userName;
    private String password;
    private String email;
    private String nickName;

    public void verify() throws BaseBadRequestException {
        boolean checkNull = userName == null || password == null || email == null || nickName == null;
        if (checkNull) throw BaseBadRequestException.RequestInvalid();

        boolean checkBlank = userName.isBlank() || password.isBlank() || email.isBlank() || nickName.isBlank();
        if (checkBlank) throw BaseBadRequestException.RequestInvalid();

    }
}
