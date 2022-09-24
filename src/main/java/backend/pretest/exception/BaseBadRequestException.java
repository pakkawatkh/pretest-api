package backend.pretest.exception;

import javax.persistence.MappedSuperclass;
import java.io.IOException;

@MappedSuperclass
public abstract class BaseBadRequestException extends IOException {
    public BaseBadRequestException(String code) {
        super(code);
    }

    public static BaseBadRequestException PasswordIncorrect(){
        return new BaseBadRequestException("รหัสผ่านไม่ถูกต้อง") {
        };
    }
    public static BaseBadRequestException UsernameDuplicate(){
        return new BaseBadRequestException("ชื่อผู้ใช้ซ้ำ") {

        };
    }
    public static BaseBadRequestException RequestInvalid(){
        return new BaseBadRequestException("ข้อมูลไม่ถูกต้อง") {
        };
    }
}
