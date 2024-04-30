package br.com.fiap.CheckPoint1.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarEnderecoDto(@NotBlank(message = "Pais não pode estar em branco!")
                                   String pais,
                                   @NotBlank(message = "Estado não pode estar em branco!")
                                   String estado,
                                   @NotBlank(message = "Cidade não pode estar em branco!")
                                   String cidade,
                                   @NotBlank(message = "Logradouro não pode estar em branco!")
                                   String logradouro,
                                   @NotBlank(message = "Cep não pode estar em branco!")
                                   @Size(min = 9, max = 9, message = "Cep precisa ter 9 caracteres!")
                                   String cep,
                                   @NotBlank(message = "Bairro não pode estar em branco!")
                                   String bairro) {
}
