package Agenda;

import Agenda.Telefone;
import dto.FileUtilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Contato {
    private UUID id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;

    public Contato(UUID id, String nome, String sobreNome, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = telefones;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void carregarTelefones() throws IOException {
        telefones = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(FileUtilities.PATH_TELEFONE))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(id.toString())) {
                    String ddd = parts[1].substring(0, 2);
                    Long numero = Long.parseLong(parts[1].substring(2));
                    telefones.add(new Telefone(ddd, numero));
                }
            }
        }
    }

    public void adicionarTelefone(Telefone telefone) {
        if (telefones == null) {
            telefones = new ArrayList<>();
        }
        telefones.add(telefone);
    }

    public void visualizarContato() {
        System.out.println(">>>> Contato <<<<");
        System.out.println("Id: " + id);
        System.out.println("Nome: " + nome + " " + sobreNome);
        System.out.println("Telefones:");
        if (telefones != null) {
            for (Telefone telefone : telefones) {
                System.out.println(telefone.formatarTelefone());
            }
        } else {
            System.out.println("Sem telefones associados.");
        }
    }
}
