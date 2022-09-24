package backend.pretest.exception;

import javax.persistence.MappedSuperclass;
import java.io.IOException;

@MappedSuperclass
public abstract class BaseNotFoundException extends IOException {
    public BaseNotFoundException(String code) {
        super(code);
    }


    public static BaseNotFoundException NotFoundContent() {
        return new BaseNotFoundException("ไม่พบข้อมูล") {
        };
    }

    public static BaseNotFoundException NotFoundUser() {
        return new BaseNotFoundException("ไม่พบชื่อผู้ใช้") {

        };
    }
}
