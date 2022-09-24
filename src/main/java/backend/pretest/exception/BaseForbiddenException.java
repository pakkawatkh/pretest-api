package backend.pretest.exception;

import javax.persistence.MappedSuperclass;
import java.io.IOException;

@MappedSuperclass
public abstract class BaseForbiddenException extends IOException {
    public BaseForbiddenException(String code) {
        super(code);
    }

    public static BaseForbiddenException Forbidden(){
        return new BaseForbiddenException("ไม่มีสิทธิ์เข้าถึง") {
        };
    }
}
