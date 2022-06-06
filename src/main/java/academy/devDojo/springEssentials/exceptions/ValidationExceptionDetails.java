package academy.devDojo.springEssentials.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionsDetails{
    private final String fields;
    private final String fieldsMessage;
}
