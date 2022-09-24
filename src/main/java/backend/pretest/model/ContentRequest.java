package backend.pretest.model;

import backend.pretest.exception.BaseBadRequestException;
import lombok.Data;

@Data
public class ContentRequest {
    private String title;
    private String description;

    public void verify() throws BaseBadRequestException {
        boolean checkNull = title == null || description == null;
        if (checkNull) throw BaseBadRequestException.RequestInvalid();

        boolean checkBlank = title.isBlank() || description.isBlank();
        if (checkBlank) throw BaseBadRequestException.RequestInvalid();
    }
}
