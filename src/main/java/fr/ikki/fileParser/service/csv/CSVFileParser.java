package fr.ikki.fileParser.service.csv;

import fr.ikki.fileParser.api.FileParser;

import java.io.File;
import java.io.IOException;

public class CSVFileParser implements FileParser<Void> {
    @Override
    public Void parse(final File file) throws IOException {
        return null;
    }
}
