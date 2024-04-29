package br.com.fiap.CheckPoint1.dto.funcionario;

import br.com.fiap.CheckPoint1.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public record CadastrarFuncionarioDto(

        @NotBlank
        String nome,
        int idade,
        String cpf,
        double salario,
        String cargo) {
}
