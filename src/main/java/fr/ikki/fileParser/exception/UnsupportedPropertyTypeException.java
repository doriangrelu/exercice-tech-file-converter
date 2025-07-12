package fr.ikki.fileParser.exception;

import lombok.NonNull;

import java.io.Serial;

public class UnsupportedPropertyTypeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 509355939164620040L;

    private static final String ERROR_MESSAGE = "Unsupported property type '%s'";

    public UnsupportedPropertyTypeException(final @NonNull String unsupportedType) {
        super(ERROR_MESSAGE.formatted(unsupportedType));
    }

}
