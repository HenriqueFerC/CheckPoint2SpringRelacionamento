package br.com.fiap.CheckPoint1.controller;

import br.com.fiap.CheckPoint1.dto.endereco.AtualizarEnderecoDto;
import br.com.fiap.CheckPoint1.dto.endereco.CadastroEnderecoDto;
import br.com.fiap.CheckPoint1.dto.endereco.DetalhesEnderecoDto;
import br.com.fiap.CheckPoint1.dto.endereco.ListagemEnderecoDto;
import br.com.fiap.CheckPoint1.model.Endereco;
import br.com.fiap.CheckPoint1.repository.EnderecoRepository;
import br.com.fiap.CheckPoint1.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity<DetalhesEnderecoDto> cadastrar(@RequestBody CadastroEnderecoDto enderecoDto, UriComponentsBuilder uriBuilder){
//        var endereco = new Endereco(enderecoDto);
//        enderecoRepository.save(endereco);
//        var url =uriBuilder.path("endereco/{id}").buildAndExpand(endereco.getId()).toUri();
//        return ResponseEntity.created(url).body(new DetalhesEnderecoDto(endereco));
//    }

    @DeleteMapping("{id}/funcionario/{idFuncionario}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id, @PathVariable("idFuncionario") Long idFuncionario){
        try {
            var funcionario = funcionarioRepository.getReferenceById(idFuncionario);
            funcionario.setEndereco(null);
            enderecoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesEnderecoDto> atualizar(@PathVariable("id") Long id, @RequestBody AtualizarEnderecoDto enderecoDto){
        var endereco = enderecoRepository.getReferenceById(id);
        endereco.atualizarEndereco(enderecoDto);
        return ResponseEntity.ok(new DetalhesEnderecoDto(endereco));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesEnderecoDto> buscarPorId(@PathVariable("id") Long id){
        try {
            var endereco = enderecoRepository.getReferenceById(id);
            return ResponseEntity.ok(new DetalhesEnderecoDto(endereco));
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ListagemEnderecoDto>> listar(Pageable pageable){

        var lista = enderecoRepository.findAll(pageable).stream().map(ListagemEnderecoDto::new).toList();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);


    }

}
