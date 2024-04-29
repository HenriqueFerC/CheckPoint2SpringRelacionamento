package br.com.fiap.CheckPoint1.controller;

import br.com.fiap.CheckPoint1.dto.endereco.CadastroEnderecoDto;
import br.com.fiap.CheckPoint1.dto.endereco.DetalhesEnderecoDto;
import br.com.fiap.CheckPoint1.dto.funcionario.AtualizarFuncionarioDto;
import br.com.fiap.CheckPoint1.dto.funcionario.CadastrarFuncionarioDto;
import br.com.fiap.CheckPoint1.dto.funcionario.DetalhesFuncionarioDto;
import br.com.fiap.CheckPoint1.dto.funcionario.ListagemFuncionarioDto;
import br.com.fiap.CheckPoint1.model.Endereco;
import br.com.fiap.CheckPoint1.model.Funcionario;
import br.com.fiap.CheckPoint1.repository.ClienteRepository;
import br.com.fiap.CheckPoint1.repository.EmpresaRepository;
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
@RequestMapping("funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity<DetalhesFuncionarioDto> cadastrar(@RequestBody CadastrarFuncionarioDto funcionarioDto, UriComponentsBuilder uriBulder){
//        var funcionario = new Funcionario(funcionarioDto);
//        funcionarioRepository.save(funcionario);
//        var url = uriBulder.path("funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
//        return ResponseEntity.created(url).body(new DetalhesFuncionarioDto(funcionario));
//    }


    @PostMapping("{id}/endereco")
    @Transactional
    public ResponseEntity<DetalhesEnderecoDto> cadastrarEndereco(@PathVariable("id") Long id, @RequestBody CadastroEnderecoDto enderecoDto, UriComponentsBuilder uriBuilder){
        var funcionario = funcionarioRepository.getReferenceById(id);
        var endereco = new Endereco(enderecoDto, funcionario);
        enderecoRepository.save(endereco);
        var url = uriBuilder.path("enderecos/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesEnderecoDto(endereco));
    }

    @PostMapping("{idFuncionario}/clientes/{idCliente}")
    @Transactional
    public ResponseEntity<DetalhesFuncionarioDto> casastrarFuncCliente(@PathVariable("idFuncionario") Long idFuncioraio, @PathVariable("idCliente") Long idCliente, UriComponentsBuilder uriBuilder){
        var funcionario = funcionarioRepository.getReferenceById(idFuncioraio);
        var cliente = clienteRepository.getReferenceById(idCliente);
        funcionario.atualizarFuncCliente(cliente);
        cliente.atualizarClienteFunc(funcionario);
        var url = uriBuilder.path("{idFuncionario}/clientes/{idClientes}").buildAndExpand(funcionario.getId(), cliente.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesFuncionarioDto(funcionario));
    }

    @DeleteMapping("{id}/empresa/{idEmpresa}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id, @PathVariable("idEmpresa") Long idEmpresa){
        try {
            var funcionario = funcionarioRepository.getReferenceById(id);
            var empresa = empresaRepository.getReferenceById(idEmpresa);
            empresa.removarFuncionario(funcionario);
            funcionarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}/endereco/{idEndereco}")
    @Transactional
    public ResponseEntity<Void> deletarEndereco(@PathVariable("id")Long id, @PathVariable("idEndereco") Long idEndereco){
        try {
            var funcionario = funcionarioRepository.getReferenceById(id);
            funcionario.setEndereco(null);
            enderecoRepository.deleteById(idEndereco);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesFuncionarioDto> atualizar(@PathVariable("id") Long id, @RequestBody AtualizarFuncionarioDto funcionarioDto){
        var funcionario = funcionarioRepository.getReferenceById(id);
        funcionario.atualizarFuncionario(funcionarioDto);
        return ResponseEntity.ok(new DetalhesFuncionarioDto(funcionario));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesFuncionarioDto> buscarPorId(@PathVariable("id") Long id){
        try {
            var funcionario = funcionarioRepository.getReferenceById(id);
            return ResponseEntity.ok(new DetalhesFuncionarioDto(funcionario));
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ListagemFuncionarioDto>> listar(Pageable pageable){
        var lista = funcionarioRepository.findAll(pageable).stream().map(ListagemFuncionarioDto::new).toList();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);

    }

}
