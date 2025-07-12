package fr.ikki.fileParser.service.meta;

import fr.ikki.fileParser.api.FileParser;
import fr.ikki.fileParser.exception.CannotParseMetadataException;
import fr.ikki.fileParser.model.FileMetaData;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class MetaFileParser implements FileParser<Collection<FileMetaData>> {

    private static final Pattern LINE_PATTERN = Pattern.compile("^([^.,\\n]+),(\\d+),(date|chaîne|numérique)$");
    private static final int NUMBER_OF_PARTS = 3;
    private static final int PROPERTY_NAME_GROUP = 1;
    private static final int PROPERTY_SIZE_GROUP = 2;
    private static final int PROPERTY_TYPE_GROUP = 3;

    @Override
    public Collection<FileMetaData> parse(final File file) throws IOException {
        log.info("Parsing file: {}", file.getAbsolutePath());
        if (!Files.exists(file.toPath())) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }
        return Files.readAllLines(file.toPath()).stream()
                .map(MetaFileParser::parseLine)
                .peek(line -> log.info("Parsed line: {}", line))
                .collect(Collectors.toUnmodifiableSet());
    }

    private static FileMetaData parseLine(final String line) {
        log.info("Parsing line: {}", line);
        final Matcher matcher = LINE_PATTERN.matcher(line);
        if (!matcher.matches() || matcher.groupCount() != NUMBER_OF_PARTS) {
            throw new CannotParseMetadataException(line);
        }

        final String propertyName = matcher.group(PROPERTY_NAME_GROUP);
        final long propertySize = Long.parseLong(matcher.group(PROPERTY_SIZE_GROUP));
        final FileMetaData.PropertyType propertyType = FileMetaData.PropertyType.fromString(matcher.group(PROPERTY_TYPE_GROUP));

        return new FileMetaData(propertyName, propertySize, propertyType);
    }

}
