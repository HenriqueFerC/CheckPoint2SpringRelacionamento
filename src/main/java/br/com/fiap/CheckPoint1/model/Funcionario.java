package br.com.fiap.CheckPoint1.model;

import br.com.fiap.CheckPoint1.dto.funcionario.AtualizarFuncClienteDto;
import br.com.fiap.CheckPoint1.dto.funcionario.AtualizarFuncionarioDto;
import br.com.fiap.CheckPoint1.dto.funcionario.CadastrarFuncionarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="TB_FUNCIONARIO")
public class Funcionario extends Pessoa{

    @Column(name="SALARIO", length = 15, nullable = false)
    private double salario;

    @Column(name="CARGO", length = 15, nullable = false)
    private String cargo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="T_FUNCIONARIO_CLIENTE", joinColumns =@JoinColumn(name="ID_CLIENTE"),
    inverseJoinColumns = @JoinColumn(name="ID_FUNCIONARIO"))
    private List<Cliente> clientes;

    @ManyToOne
    @JoinColumn(name="ID_EMPRESA")
    private Empresa empresa;

    @OneToOne(mappedBy = "funcionario",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Endereco endereco;

    public Funcionario(Long id, String nome, int idade, String cpf, double salario, String cargo) {
        super(id, nome, idade, cpf);
        this.salario = salario;
        this.cargo = cargo;
    }

    public Funcionario(CadastrarFuncionarioDto funcionarioDto, Empresa empresa){
        nome = funcionarioDto.nome();
        idade = funcionarioDto.idade();
        cpf = funcionarioDto.cpf();
        salario = funcionarioDto.salario();
        cargo = funcionarioDto.cargo();
        this.empresa = empresa;
    }

    public void atualizarFuncionario(AtualizarFuncionarioDto funcionarioDto){
        nome = funcionarioDto.nome();
        idade = funcionarioDto.idade();
        cpf = funcionarioDto.cpf();
        salario = funcionarioDto.salario();
        cargo = funcionarioDto.cargo();
    }

    public void atualizarFuncCliente(Cliente cliente){
        clientes.add(cliente);
    }
}
