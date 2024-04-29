package br.com.fiap.CheckPoint1.model;

import br.com.fiap.CheckPoint1.dto.endereco.AtualizarEnderecoDto;
import br.com.fiap.CheckPoint1.dto.endereco.CadastroEnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="TB_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue
    @Column(name="ID_ENDERECO")
    private Long id;

    @Column(name="PAIS", length = 20, nullable = false)
    private String pais;

    @Column(name="ESTADO", length = 20, nullable = false)
    private String estado;

    @Column(name="CIDADE", length = 20, nullable = false)
    private String cidade;

    @Column(name="LOGRADOURO", length = 20, nullable = false)
    private String logradouro;

    @Column(name="CEP", length = 9, nullable = false)
    private String cep;

    @Column(name="BAIRRO", length = 20, nullable = false)
    private String bairro;

    @OneToOne
    @JoinColumn(name="ID_FUNCIONARIO")
    private Funcionario funcionario;

    public Endereco(CadastroEnderecoDto enderecoDto, Funcionario funcionario) {
        pais = enderecoDto.pais();
        estado = enderecoDto.estado();
        cidade = enderecoDto.cidade();
        logradouro = enderecoDto.logradouro();
        cep = enderecoDto.cep();
        bairro = enderecoDto.bairro();
        this.funcionario = funcionario;
    }

    public void atualizarEndereco(AtualizarEnderecoDto enderecoDto){
        pais = enderecoDto.pais();
        estado = enderecoDto.estado();
        cidade = enderecoDto.cidade();
        logradouro = enderecoDto.logradouro();
        cep = enderecoDto.cep();
        bairro = enderecoDto.bairro();
    }

}
