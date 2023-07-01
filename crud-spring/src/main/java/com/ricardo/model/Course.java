package com.ricardo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data //Esta anotação do Lombok é equivalente a getter, setter, equalsAndHashCode
@Entity //Pertence ao pacote JPA, avisa ao spring que esta classe é uma entidade mapeada para o banco de dados
@SQLDelete(sql = "UPDATE Course SET status='inativo' WHERE id=?")
@Where(clause = "status = 'ativo'")
public class Course {

    @Id //Informando ao spring que este campo será a chave primaria da tabela
    @GeneratedValue(strategy = GenerationType.AUTO) //Diz para o banco de dados que a chave será gerada automaticamente
    @JsonProperty("_id") //Faz o ajuste entre o front-end e o back-end em relação aos nomes dados aos atributos em ambos os lados.
    private Long id;

    //Informa que é uma coluna da tabela e que o tamanho máximo é 200 caracteres e não aceita null
    @Column(length = 100, nullable = false)
    @NotNull
    @Length(min = 5, max = 100)
    private String name;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "back-end|front-end")
    @Column(length = 10, nullable = false)
    private String category;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "ativo|inativo")
    @Column(length = 10, nullable = false)
    private String status = "ativo";
}
