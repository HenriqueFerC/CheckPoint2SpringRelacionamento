package br.com.fiap.CheckPoint1.dto.funcionario;

import br.com.fiap.CheckPoint1.model.Endereco;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastrarFuncionarioDto(

        @NotBlank(message = "Nome não pode estar em branco!")
        String nome,
        @NotNull(message = "Idade não pode ser nula!")
        @Size(min = 1, max = 3, message = "Idade deve conter no mínimo 1 e no máximo 3 números!")
        int idade,
        @NotBlank(message = "Cpf não pode estar em branco!")
        @Size(min = 11, max = 14, message = "Cpf inválido!")
        String cpf,
        @NotNull(message = "Salário não pode ser nulo")
        @Min(value = 0, message = "Salário precisa ser um valor positivo!")
        double salario,
        @NotBlank(message = "Cargo não pode estar em branco!")
        String cargo) {
}
