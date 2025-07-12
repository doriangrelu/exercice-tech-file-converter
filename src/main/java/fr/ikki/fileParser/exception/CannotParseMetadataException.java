package fr.ikki.fileParser.exception;

import lombok.NonNull;

import java.io.Serial;

public class CannotParseMetadataException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6642348346779432482L;

    private static final String ERROR_MESSAGE = "Cannot parse line '%s' (format attendu: .*,\\d+,chaîne|date|numérique)";

    public CannotParseMetadataException(final @NonNull String targetLine) {
        super(ERROR_MESSAGE.formatted(targetLine));
    }

}
