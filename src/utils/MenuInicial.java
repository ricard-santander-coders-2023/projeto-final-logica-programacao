package utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import static utils.AcaoMenu.executaAcaoMenu;

public class MenuInicial {
    static public void menu() {
        Scanner sc = new Scanner(System.in);
        String menuText = """
                >>>> Menu <<<<
                1 - Adicionar Contato
                2 - Remover Contato
                3 - Visualizar Contato
                4 - Editar Contato
                5 - Mostrar Contatos
                0 - Sair
                """;

        int option = -1;
        while (option != 0) {
            System.out.println(menuText);
            try {
                option = sc.nextInt();
                sc.nextLine();
                if (option < 0 || option > 5) {
                    throw new InputMismatchException();
                }
                executaAcaoMenu(option);
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida.");
                System.out.println("Digite um número de 0 a 5!");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
        System.out.println("Saindo...");
        sc.close();
    }
}
