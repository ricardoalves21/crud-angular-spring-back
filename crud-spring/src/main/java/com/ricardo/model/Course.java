package com.ricardo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricardo.enums.Category;
import com.ricardo.enums.Status;
import com.ricardo.enums.converters.CategoryConverter;
import com.ricardo.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

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
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

    // Este atributo é para associação entre esta classe e a classe Lesson
    // A anotação 'cascade' serve para que qualquer alteração nesta entidade Course seja passada também para as classes filhas como 'Lesson'
    // A anotação 'orphanRemoval' remove todos os registros que ficarem orfaos nesta relação entre a classe mae e as filhas
    // A anotação @JoinColumn faz a união entre a entidade 'Course' e a 'Lesson' definindo um nome de coluna que unirá as duas entidades.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course") //
    //@JoinColumn(name = "course_id")
    private List<Lesson> lessons = new ArrayList<>();

}
