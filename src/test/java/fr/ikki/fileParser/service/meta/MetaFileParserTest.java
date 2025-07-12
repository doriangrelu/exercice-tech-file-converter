package fr.ikki.fileParser.service.meta;

import fr.ikki.fileParser.api.FileParser;
import fr.ikki.fileParser.exception.CannotParseMetadataException;
import fr.ikki.fileParser.model.FileMetaData;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MetaFileParserTest {

    private final FileParser<Collection<FileMetaData>> subject = new MetaFileParser();

    @Test
    @DisplayName("GIVEN un fichier de métadonnées correct WHEN le système parse ce dernier THEN nous obtenons les métadonnées")
    void test_parseFile_OkFile() throws IOException, URISyntaxException {
        //GIVEN
        final File targetFile = get("jdd/meta/00_OK.txt");

        //WHEN
        final Collection<FileMetaData> result = this.subject.parse(targetFile);

        //THEN
        assertThat(result).containsAll(expected());
    }


    @Test
    @DisplayName("GIVEN un fichier de métadonnées incorrect (mauvais format) WHEN le système parse ce dernier THEN nous obtenons une erreur")
    void test_parseFile_KOFileFormat() throws IOException, URISyntaxException {
        //GIVEN
        final File targetFile = get("jdd/meta/01_KO_FORMAT.txt");

        //WHEN, THEN
        assertThatThrownBy(() -> this.subject.parse(targetFile))
                .isInstanceOf(CannotParseMetadataException.class)
                .hasMessage("Cannot parse line 'Date de naissanc10,date' (format attendu: .*,\\d+,chaîne|date|numérique)");

    }

    @Test
    @DisplayName("GIVEN un fichier de métadonnées incorrect (mauvais format) WHEN le système parse ce dernier THEN nous obtenons une erreur")
    void test_parseFile_KOFilePropertyType() throws IOException, URISyntaxException {
        //GIVEN
        final File targetFile = get("jdd/meta/02_KO_TYPE.txt");

        //WHEN, THEN
        assertThatThrownBy(() -> this.subject.parse(targetFile))
                .isInstanceOf(CannotParseMetadataException.class)
                .hasMessage("Cannot parse line 'Date de naissance,10,datetime' (format attendu: .*,\\d+,chaîne|date|numérique)");

    }

    private static Collection<FileMetaData> expected() {
        return Set.of(
                FileMetaData.builder().propertyName("Nom de famille").propertySize(15).propertyType(FileMetaData.PropertyType.STRING).build(),
                FileMetaData.builder().propertyName("Prénom").propertySize(15).propertyType(FileMetaData.PropertyType.STRING).build(),
                FileMetaData.builder().propertyName("Poids").propertySize(5).propertyType(FileMetaData.PropertyType.NUMBER).build(),
                FileMetaData.builder().propertyName("Date de naissance").propertySize(10).propertyType(FileMetaData.PropertyType.DATE).build()
        );
    }

    private static File get(final @NonNull String path) throws URISyntaxException {
        final URL url = MetaFileParserTest.class.getClassLoader().getResource(path);
        Assertions.assertNotNull(url);
        return Paths.get(url.toURI()).toFile();
    }

}
