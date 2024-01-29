package utils;

import java.io.IOException;
import java.util.Scanner;
import agenda.Contato;

public class AcaoMenu {

    public static void executaAcaoMenu(int opcao) throws IOException {
        Scanner scanner = new Scanner(System.in);
        switch (opcao) {
            case 1:
                System.out.println("Adicionar Contato:");
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Sobrenome: ");
                String sobrenome = scanner.nextLine();

                Contato contato = new Contato(Contato.getId(), nome, sobrenome, "");

                try {
                    Contato.adicionarContato(contato);
                } catch (IOException e) {
                    System.out.println("Erro ao adicionar contato: " + e.getMessage());
                }
                break;
            case 2:
                System.out.println("Remover Contato:");
                System.out.print("ID do Contato: ");
                Long idRemover = scanner.nextLong();
                Contato.removeContato(idRemover);
                break;
            case 3:
                System.out.println("Editar Contato:");
                System.out.print("ID do Contato: ");
                Long idEditar = scanner.nextLong();
                Contato.editarContato(idEditar);
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        MenuInicial.menu();
    }
}
