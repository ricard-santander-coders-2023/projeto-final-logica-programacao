package Agenda;

import java.util.UUID;

public class Telefone {
    private UUID id;
    private String telefone;

    public Telefone(UUID id, String telefone) {
        this.id = id;
        this.telefone = telefone;
    }

    public String formatarTelefone() {
        String telefoneFormatado = telefone.replaceAll("\\D", ""); // Remove todos os caracteres não numéricos
        if (telefoneFormatado.length() == 8 || telefoneFormatado.length() == 9) {
            return telefoneFormatado.substring(0, 4) + "-" + telefoneFormatado.substring(4);
        } else {
            return telefoneFormatado;
        }
    }
}
