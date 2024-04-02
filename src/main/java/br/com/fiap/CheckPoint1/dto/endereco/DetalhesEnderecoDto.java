package br.com.fiap.CheckPoint1.dto.endereco;

import br.com.fiap.CheckPoint1.model.Endereco;

public record DetalhesEnderecoDto(Long id, String pais, String estado, String cidade, String logradouro, String cep, String bairro) {
    public DetalhesEnderecoDto(Endereco endereco){
        this(endereco.getId(), endereco.getPais(), endereco.getEstado(), endereco.getCidade(), endereco.getLogradouro(), endereco.getCep(), endereco.getBairro());
    }
}
