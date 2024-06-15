package org.chat.investpro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WriteToCsvTest {

    private CsvWriter writer = new CsvWriter();

    @BeforeEach
    public void setUp() {
        writer = new CsvWriter();
    }

    @Test
    public void testWriteToCSV_ValidObjectAndValidVorm() throws IOException {
        IinvesteeringsVorm object = mock(IinvesteeringsVorm.class);
        when(object.getNaam()).thenReturn("Naam");
        when(object.getWaardeBijAankoop()).thenReturn(100.0);
        when(object.getAankoopPrijs()).thenReturn(10.0);
        when(object.getAantal()).thenReturn(1.0);


        assertDoesNotThrow(() -> writer.writeToCSV(object, "aandeel"));
        Path path = Paths.get("aandeel.csv");
        closeAndDeleteFile(path);
    }

    private void closeAndDeleteFile(Path path) throws IOException {
        Files.delete(path);
    }

}
