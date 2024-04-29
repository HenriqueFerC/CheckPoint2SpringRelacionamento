package br.com.fiap.CheckPoint1.model;

import br.com.fiap.CheckPoint1.dto.empresa.AtualizarEmpresaDto;
import br.com.fiap.CheckPoint1.dto.empresa.CadastroEmpresaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaAttributeTypeInspection")
@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="TB_EMPRESA")
public class Empresa {

    @Id
    @GeneratedValue
    @Column(name="ID_EMPRESA")
    private Long id;

    @Column(name="NM_EMPRESA", length = 50, nullable = false)
    private String nome;

    @Column(name="CNPJ", length = 14, nullable = false)
    private String cnpj;

    @Column(name="TIPO_EMPRESA", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipo;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Departamento> departamentos;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Empresa(CadastroEmpresaDto empresaDto){
        nome = empresaDto.nome();
        cnpj = empresaDto.cnpj();
        tipo = empresaDto.tipo();
//        departamento = empresaDto.departamento();
//        funcionario = empresaDto.funcionario();
    }

    public void atualizarEmpresa(AtualizarEmpresaDto empresaDto){
        nome = empresaDto.nome();
        cnpj = empresaDto.cnpj();
        tipo = empresaDto.tipo();
    }

    public void adicionarFuncionaro(Funcionario funcionarioNovo){
        funcionarioNovo.setEmpresa(this);
        funcionarios.add(funcionarioNovo);
    }

    public void adicionarDepartamento(Departamento departamentoNovo){
        departamentoNovo.setEmpresa(this);
        departamentos.add(departamentoNovo);
    }

    public void removerDepartamento(Departamento departamento){
        departamentos.remove(departamento);
    }

    public void removarFuncionario(Funcionario funcionario){
        funcionarios.remove(funcionario);
    }

}
