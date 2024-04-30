package br.com.fiap.CheckPoint1.dto.empresa;

import br.com.fiap.CheckPoint1.model.TipoEmpresa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroEmpresaDto(@NotBlank(message = "Nome não pode estar em branco!")
                                String nome,
                                 @NotBlank(message = "Cnpj não pode estar em branco!")
                                 @Size(min = 14, max = 14, message = "CNPJ precisa ter 14 caracteres!")
                                 String cnpj,
                                 @NotNull(message = "Tipo de empresa é obrigatório!")
                                 TipoEmpresa tipo ){
}
