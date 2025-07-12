package fr.ikki.fileParser.api;

import java.io.File;
import java.io.IOException;

public interface FileParser<T> {

    T parse(File file) throws IOException;

}
