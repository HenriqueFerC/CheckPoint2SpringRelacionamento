package br.com.fiap.CheckPoint1.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtualizarClienteDto(@NotBlank(message = "Nome não pode estar em branco!")
                                  String nome,
                                  @NotNull(message = "Idade não pode ser nula!")
                                  @Size(min = 1, max = 3, message = "Idade deve conter no mínimo 1 e no máximo 3 números!")
                                  int idade,
                                  @NotBlank(message = "Cpf não pode estar em branco!")
                                  @Size(min = 11, max = 14, message = "Cpf inválido!")
                                  String cpf) {
}
