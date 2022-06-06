package academy.devDojo.springEssentials.exceptions;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionsDetails {
    protected String title;
    protected int status;
    protected String details;
    protected String message;
    protected LocalDateTime timestamp;
}
