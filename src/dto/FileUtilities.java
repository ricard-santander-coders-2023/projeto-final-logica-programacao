package dto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtilities {

    public static final String PATH_CONTATO = "./files/contatos.txt";
    public static final String PATH_TELEFONE = "./files/telefones.txt";

    private File contatos;
    private File telefones;

    public FileUtilities() {
        contatos = new File(PATH_CONTATO);
        if (!contatos.exists()) {
            try {
                contatos.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        telefones = new File(PATH_TELEFONE);
        if (!telefones.exists()) {
            try {
                telefones.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void openFile(String dataType, String action) throws IOException {
        File file = getFileByType(dataType);

        if (action.equals("read")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } else if (action.equals("write")) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                // Adicione aqui o conteúdo que você deseja escrever no arquivo
                writer.write("Conteúdo a ser escrito");
            }
        } else {
            throw new IllegalArgumentException("Ação inválida: " + action);
        }
    }

    public void closeFile(String dataType) {
        // Não é necessário fechar explicitamente os arquivos em Java, pois o try-with-resources já faz isso automaticamente
    }

    private File getFileByType(String dataType) {
        switch (dataType) {
            case "contato":
                return contatos;
            case "telefones":
                return telefones;
            default:
                throw new IllegalArgumentException("Tipo de dado inválido: " + dataType);
        }
    }
}
