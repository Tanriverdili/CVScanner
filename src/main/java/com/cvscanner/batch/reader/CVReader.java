package com.cvscanner.batch.reader;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

@Component
public class CVReader implements ItemReader<Path> {
    private Iterator<Path> iterator;

    public CVReader() throws IOException {
        Path folder = Paths.get("temp-cvs");
        this.iterator = (Iterator<Path>) Files.list(folder).iterator();
    }
    @Override
    public Path read() {
        if (iterator.hasNext()) {
            return Path.of(iterator.next().toString());
        }
        return null;
    }
}