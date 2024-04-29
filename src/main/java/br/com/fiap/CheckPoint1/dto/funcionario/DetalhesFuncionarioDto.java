package br.com.fiap.CheckPoint1.dto.funcionario;

import br.com.fiap.CheckPoint1.dto.cliente.DetalhesClienteDto;
import br.com.fiap.CheckPoint1.dto.empresa.DetalhesEmpresaDto;
import br.com.fiap.CheckPoint1.model.Endereco;
import br.com.fiap.CheckPoint1.model.Funcionario;

import java.util.List;

public record DetalhesFuncionarioDto(Long id, String nome, int idade, String cpf, double salario, String cargo, DetalhesEmpresaDto detalheDto) {
    public DetalhesFuncionarioDto(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getIdade(), funcionario.getCpf(), funcionario.getSalario(), funcionario.getCargo(), new DetalhesEmpresaDto(funcionario.getEmpresa()));
    }
}
