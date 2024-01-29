package Agenda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static dto.FileUtilities.PATH_CONTATO;

public class Contato {
    private static Long id = 0L;
    private String nome;
    private String sobrenome;
    private String telefone;

    public Contato(Long id, String nome, String sobreNome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobreNome;
        this.telefone = telefone;
        Contato.id++;
    }

    public static Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public static void adicionarContato(Contato contato) throws IOException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(PATH_CONTATO, true));
            writer.println(contato.getId() + "|" + contato.getNome() + "|" + contato.getSobrenome() + "|" + contato.getTelefone());
            writer.close();
            System.out.println("Contato adicionado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeContato(Long id) throws IOException {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_CONTATO))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                Long idContato = Long.parseLong(partes[0]);

                if (!idContato.equals(id)) {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(PATH_CONTATO))) {
            for (String linha : linhas) {
                writer.println(linha);
            }
        }

        System.out.println("Contato removido com sucesso.");
    }

    public static void editarContato(Long idEditar) throws IOException {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_CONTATO))) {
            String linha;
            boolean contatoEncontrado = false;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                Long idContato = Long.parseLong(partes[0]);

                if (idContato.equals(idEditar)) {
                    contatoEncontrado = true;
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Digite o novo nome do contato:");
                    String novoNome = scanner.nextLine();
                    System.out.println("Digite o novo sobrenome do contato:");
                    String novoSobrenome = scanner.nextLine();
                    System.out.println("Digite o novo telefone do contato:");
                    String novoTelefone = scanner.nextLine();
                    linha = idEditar + "|" + novoNome + "|" + novoSobrenome + "|" + novoTelefone;
                }
                linhas.add(linha);
            }

            if (!contatoEncontrado) {
                System.out.println("Contato não encontrado.");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(PATH_CONTATO))) {
            for (String linha : linhas) {
                writer.println(linha);
            }
        }

        System.out.println("Contato editado com sucesso.");
    }

    public static void exibirContatos() {
        System.out.println();
        String menuAgenda = """
        ##################
        ##### AGENDA #####
        ##################
        
        >>>> Contatos <<<<
        """;

        System.out.println(menuAgenda);

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_CONTATO))) {
            String linha;

            System.out.println("Id | Nome     | Telefone");

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                String id = partes[0].trim();
                String nome = partes[1].trim();
                String sobrenome = partes[2].trim();
                String telefone = partes[3].trim();
                System.out.println(id + " | " + nome + " " + sobrenome + " | " + telefone);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
