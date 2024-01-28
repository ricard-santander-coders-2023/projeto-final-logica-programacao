package utils;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;
import Agenda.Contato;

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
                Contato contato = new Contato(UUID.randomUUID(), nome, sobrenome, null);
                Contato.adicionarContato(contato);
                break;
            case 2:
                System.out.println("Remover Contato:");
                System.out.print("ID do Contato: ");
                UUID idRemover = UUID.fromString(scanner.nextLine());
                Contato.removeContato(idRemover);
                break;
            case 3:
                System.out.println("Visualizar Contato:");
                System.out.print("ID do Contato: ");
                UUID idVisualizar = UUID.fromString(scanner.nextLine());
                Contato.visualizarContato(idVisualizar);
                break;
            case 4:
                System.out.println("Editar Contato:");
                System.out.print("ID do Contato: ");
                UUID idEditar = UUID.fromString(scanner.nextLine());
                Contato.editarContato(idEditar);
                break;
            case 5:
                System.out.println("Mostrar Contatos:");
                Contato.mostrarContatos();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        MenuInicial.menu();
    }
}
