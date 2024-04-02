package br.com.fiap.CheckPoint1.model;

import br.com.fiap.CheckPoint1.dto.departamento.AtualizarDepartamentoDto;
import br.com.fiap.CheckPoint1.dto.departamento.CadastroDepartamentoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor


@Entity
@Table(name="TB_DEPARTAMENTO")
public class Departamento {

    @Id
    @GeneratedValue
    @Column(name="ID_DEPARTMAENTO")
    private Long id;

    @Column(name="TIPO_DEPARTAMENTO", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDepartamento tipo;

    public Departamento(CadastroDepartamentoDto departamentoDto){
        tipo = departamentoDto.tipo();
    }

    public void atualizarDepartamento(AtualizarDepartamentoDto departamentoDto){
        tipo = departamentoDto.tipo();
    }
}