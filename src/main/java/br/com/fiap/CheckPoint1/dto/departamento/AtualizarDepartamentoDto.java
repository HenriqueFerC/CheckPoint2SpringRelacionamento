package br.com.fiap.CheckPoint1.dto.departamento;

import br.com.fiap.CheckPoint1.model.TipoDepartamento;
import jakarta.validation.constraints.NotNull;

public record AtualizarDepartamentoDto(@NotNull(message = "Tipo de departamento é obrigatório!")
                                       TipoDepartamento tipo) {
}
