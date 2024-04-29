package br.com.fiap.CheckPoint1.controller;

import br.com.fiap.CheckPoint1.dto.departamento.CadastroDepartamentoDto;
import br.com.fiap.CheckPoint1.dto.departamento.DetalhesDepartamentoDto;
import br.com.fiap.CheckPoint1.dto.empresa.AtualizarEmpresaDto;
import br.com.fiap.CheckPoint1.dto.empresa.CadastroEmpresaDto;
import br.com.fiap.CheckPoint1.dto.empresa.DetalhesEmpresaDto;
import br.com.fiap.CheckPoint1.dto.empresa.ListagemEmpresaDto;
import br.com.fiap.CheckPoint1.dto.funcionario.CadastrarFuncionarioDto;
import br.com.fiap.CheckPoint1.dto.funcionario.DetalhesFuncionarioDto;
import br.com.fiap.CheckPoint1.model.Departamento;
import br.com.fiap.CheckPoint1.model.Empresa;
import br.com.fiap.CheckPoint1.model.Funcionario;
import br.com.fiap.CheckPoint1.repository.DepartamentoRepository;
import br.com.fiap.CheckPoint1.repository.EmpresaRepository;
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
@RequestMapping("empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEmpresaDto> cadastrar(@RequestBody CadastroEmpresaDto empresaDto, UriComponentsBuilder uriBuilder){
        var empresa = new Empresa(empresaDto);
        empresaRepository.save(empresa);
        var url = uriBuilder.path("empresas/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesEmpresaDto(empresa));
    }

    @PostMapping("{id}/departamentos")
    @Transactional
    public ResponseEntity<DetalhesDepartamentoDto> cadastrarDepartamento(@PathVariable("id") Long id, @RequestBody CadastroDepartamentoDto departamentoDto, UriComponentsBuilder uriBuilder){
        var empresa = empresaRepository.getReferenceById(id);
        var departamento = new Departamento(departamentoDto, empresa);
        departamentoRepository.save(departamento);
        var uri= uriBuilder.path("departamentos/{id}").buildAndExpand(departamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesDepartamentoDto(departamento));
    }

    @PostMapping("{id}/funcionarios")
    @Transactional
    public ResponseEntity<DetalhesFuncionarioDto> cadastrarFuncionario(@PathVariable("id") Long id, @RequestBody CadastrarFuncionarioDto funcionarioDto, UriComponentsBuilder uriBuilder){
        var empresa = empresaRepository.getReferenceById(id);
        var funcionario = new Funcionario(funcionarioDto, empresa);
        funcionarioRepository.save(funcionario);
        var url = uriBuilder.path("funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesFuncionarioDto(funcionario));
    }

    @DeleteMapping("{idEmpresa}/departamentos/{idDepartamento}")
    @Transactional
    public ResponseEntity<Void> deletarDepartamento(@PathVariable("idEmpresa")Long idEmpresa, @PathVariable("idDepartamento") Long idDepartamento){
        try {
            var empresa = empresaRepository.getReferenceById(idEmpresa);
            var departamento = departamentoRepository.getReferenceById(idDepartamento);
            empresa.removerDepartamento(departamento);
            departamentoRepository.deleteById(idDepartamento);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{idEmpresa}/funcionarios/{idFuncionario}")
    @Transactional
    public ResponseEntity<Void> deletarFuncionario(@PathVariable("idEmpresa")Long idEmpresa, @PathVariable("idFuncionario") Long idFuncionario){
        try {
            var empresa = empresaRepository.getReferenceById(idEmpresa);
            var funcionario = funcionarioRepository.getReferenceById(idFuncionario);
            empresa.removarFuncionario(funcionario);
            funcionarioRepository.deleteById(idFuncionario);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        try {
            empresaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesEmpresaDto> atualizar(@PathVariable("id") Long id, @RequestBody AtualizarEmpresaDto empresaDto){
        var empresa = empresaRepository.getReferenceById(id);
        empresa.atualizarEmpresa(empresaDto);
        return ResponseEntity.ok(new DetalhesEmpresaDto(empresa));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesEmpresaDto> buscarPorId(@PathVariable("id") Long id){
        try {
            var empresa = empresaRepository.getReferenceById(id);
            return ResponseEntity.ok(new DetalhesEmpresaDto(empresa));
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ListagemEmpresaDto>> listar(Pageable pageable){

        var lista = empresaRepository.findAll(pageable).stream().map(ListagemEmpresaDto::new).toList();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }



}
