package dto;

import java.io.File;
import java.io.IOException;

public class FileUtilities {

    public static final String PATH_CONTATO = "./contatos.txt";

    private File contatos;

    public FileUtilities() {
        contatos = new File(PATH_CONTATO);
        if (!contatos.exists()) {
            try {
                contatos.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
