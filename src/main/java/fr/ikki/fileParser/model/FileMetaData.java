package fr.ikki.fileParser.model;

import fr.ikki.fileParser.exception.UnsupportedPropertyTypeException;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Builder(toBuilder = true)
public record FileMetaData(
        String propertyName,
        long propertySize,
        PropertyType propertyType
) {

    @RequiredArgsConstructor
    @Getter
    public enum PropertyType {

        STRING("chaîne"), DATE("date"), NUMBER("numérique");

        private final String rawName;

        public static PropertyType fromString(final @NonNull String value) throws UnsupportedPropertyTypeException {
            return Stream.of(PropertyType.values())
                    .filter(property -> property.rawName().equals(value))
                    .findFirst()
                    .orElseThrow(() -> new UnsupportedPropertyTypeException(value));
        }

    }

}
