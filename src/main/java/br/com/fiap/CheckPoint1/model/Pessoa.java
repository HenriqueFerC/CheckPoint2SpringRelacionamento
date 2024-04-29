package br.com.fiap.CheckPoint1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue
    @Column(name="ID")
    protected Long id;

    @Column(name="NOME", length = 50, nullable = false)
    protected String nome;

    @Column(name="IDADE", length = 3, nullable = false)
    protected int idade;

    @Column(name="CPF", length = 14, nullable = false)
    protected String cpf;






}
