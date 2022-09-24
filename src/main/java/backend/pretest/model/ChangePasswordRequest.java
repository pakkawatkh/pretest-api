package backend.pretest.model;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String passwordOld;
    private String passwordNew;
}
