package fr.ikki.fileParser.factory;

import fr.ikki.fileParser.api.FileParser;
import fr.ikki.fileParser.model.FileMetaData;
import fr.ikki.fileParser.service.meta.MetaFileParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileParserFactory {

    public static FileParser<Collection<FileMetaData>> createMetaFileParser() {
        return new MetaFileParser();
    }

}
