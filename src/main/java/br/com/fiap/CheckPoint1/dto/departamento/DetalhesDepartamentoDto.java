package br.com.fiap.CheckPoint1.dto.departamento;

import br.com.fiap.CheckPoint1.dto.empresa.DetalhesEmpresaDto;
import br.com.fiap.CheckPoint1.model.Departamento;
import br.com.fiap.CheckPoint1.model.TipoDepartamento;

public record DetalhesDepartamentoDto(Long id, TipoDepartamento tipo, DetalhesEmpresaDto detalhesEmpresa) {
    public DetalhesDepartamentoDto(Departamento departamento){
        this(departamento.getId(), departamento.getTipo(), new DetalhesEmpresaDto(departamento.getEmpresa()));
    }
}
