package com.pfc.thindesk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vagas")
@Data // Gera getters, setters, toString(), hashCode(), e equals().
@NoArgsConstructor // Gera o construtor sem argumentos
@AllArgsConstructor // Gera o construtor com todos os argumentos
public class Vaga {

    @Id
    private String id;
    private String setor;
    private String diaSemana;
    private String horarioInicio;
    private String horarioFim;
}