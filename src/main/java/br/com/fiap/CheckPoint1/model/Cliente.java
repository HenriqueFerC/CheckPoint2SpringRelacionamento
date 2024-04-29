package br.com.fiap.CheckPoint1.model;

import br.com.fiap.CheckPoint1.dto.cliente.AtualizarClienteDto;
import br.com.fiap.CheckPoint1.dto.cliente.AtualizarClienteFuncDto;
import br.com.fiap.CheckPoint1.dto.cliente.CadastroClienteDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="TB_CLIENTE")
public class Cliente extends Pessoa{


    public Cliente(Long id, String nome, int idade, String cpf) {
        super(id, nome, idade, cpf);
    }


    @ManyToMany(mappedBy = "clientes", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

    public Cliente(CadastroClienteDto clienteDto){
        nome = clienteDto.nome();
        idade = clienteDto.idade();
        cpf = clienteDto.cpf();
    }

    public Cliente(CadastroClienteDto clienteDto, Funcionario funcionario){
        nome = clienteDto.nome();
        idade = clienteDto.idade();
        cpf = clienteDto.cpf();
        funcionarios.add(funcionario);
    }

    public void atualizarCLiente(AtualizarClienteDto clienteDto){
        nome = clienteDto.nome();
        idade = clienteDto.idade();
        cpf = clienteDto.cpf();
    }

    public void atualizarClienteFunc(Funcionario funcionario){
        funcionarios.add(funcionario);
    }

}
