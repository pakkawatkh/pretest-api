package backend.pretest.exception;

import javax.persistence.MappedSuperclass;
import java.io.IOException;

@MappedSuperclass
public abstract class BaseInternalServerErrorException extends IOException {
    public BaseInternalServerErrorException(String code) {
        super(code);
    }

    public static BaseInternalServerErrorException SaveError() {
        return new BaseInternalServerErrorException("ไม่สามารถบันทึกข้อมูลได้") {

        };
    }
}
