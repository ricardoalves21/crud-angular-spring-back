package com.ricardo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data //Esta anotação do Lombok é equivalente a getter, setter, equals e hashcode
@Entity //Pertence ao pacote JPA, avisa ao spring que esta classe é uma entidade mapeada para o banco de dados
public class Course {

    @Id //Informando ao spring que este campo será a chave primaria da tabela
    @GeneratedValue(strategy = GenerationType.AUTO) //Diz para o banco de dados que a chave será gerada automaticamente
    private Long id;

    //Informa que é uma coluna da tabela e que o tamanho máximo é 200 caracteres e não aceita null
    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String Category;
}
