package backend.pretest.model;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String email;
    private String nickName;
}
