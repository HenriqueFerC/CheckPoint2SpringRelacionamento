package br.com.fiap.CheckPoint1.controller;

import br.com.fiap.CheckPoint1.dto.departamento.AtualizarDepartamentoDto;
import br.com.fiap.CheckPoint1.dto.departamento.CadastroDepartamentoDto;
import br.com.fiap.CheckPoint1.dto.departamento.DetalhesDepartamentoDto;
import br.com.fiap.CheckPoint1.dto.departamento.ListagemDepartamentoDto;
import br.com.fiap.CheckPoint1.model.Departamento;
import br.com.fiap.CheckPoint1.repository.DepartamentoRepository;
import br.com.fiap.CheckPoint1.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity<DetalhesDepartamentoDto> cadastrar(@RequestBody CadastroDepartamentoDto departamentoDto, UriComponentsBuilder uriBuilder){
//        var departamento = new Departamento(departamentoDto);
//        departamentoRepository.save(departamento);
//        var url = uriBuilder.path("departamentos/{id}").buildAndExpand(departamento.getId()).toUri();
//    return ResponseEntity.created(url).body(new DetalhesDepartamentoDto(departamento));
//    }

    @DeleteMapping("{id}/empresa/{idEmpresa}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id, @PathVariable("idEmpresa") Long idEmpresa){
        try {
            var departamento = departamentoRepository.getReferenceById(id);
            var empresa = empresaRepository.getReferenceById(idEmpresa);
            empresa.removerDepartamento(departamento);
            departamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesDepartamentoDto> atualizar(@PathVariable("id") Long id, @RequestBody AtualizarDepartamentoDto departamentoDto){
        var departamento = departamentoRepository.getReferenceById(id);
        departamento.atualizarDepartamento(departamentoDto);
        return ResponseEntity.ok(new DetalhesDepartamentoDto(departamento));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesDepartamentoDto> buscarPorId(@PathVariable("id") Long id){
        try {
            var departamento = departamentoRepository.getReferenceById(id);
            return ResponseEntity.ok(new DetalhesDepartamentoDto(departamento));
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ListagemDepartamentoDto>> listar(Pageable pageable){

        var lista = departamentoRepository.findAll(pageable).stream().map(ListagemDepartamentoDto::new).toList();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }


}
