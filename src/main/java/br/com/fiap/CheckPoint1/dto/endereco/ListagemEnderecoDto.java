package br.com.fiap.CheckPoint1.dto.endereco;

import br.com.fiap.CheckPoint1.dto.funcionario.DetalhesFuncionarioDto;
import br.com.fiap.CheckPoint1.model.Endereco;

public record ListagemEnderecoDto(Long id, String pais, String estado, String cidade, String logradouro, String cep, String bairro, DetalhesFuncionarioDto detalhesDto) {
    public ListagemEnderecoDto(Endereco endereco){
        this(endereco.getId(), endereco.getPais(), endereco.getEstado(), endereco.getCidade(), endereco.getLogradouro(), endereco.getCep(), endereco.getBairro(), new DetalhesFuncionarioDto(endereco.getFuncionario()));
    }
}
