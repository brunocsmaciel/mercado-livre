package github.macices.mercado_livre;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtils {


    public static String lerArquivo(String nomeArquivo) {

        ClassPathResource resource = new ClassPathResource(nomeArquivo);
        try {
            Path path = Paths.get(resource.getURI());
            byte[] arquivo = Files.readAllBytes(path);
            return new String(arquivo, StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
