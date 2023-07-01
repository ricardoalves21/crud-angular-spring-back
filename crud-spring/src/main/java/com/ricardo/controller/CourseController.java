package com.ricardo.controller;


import com.ricardo.model.Course;
import com.ricardo.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController //Esta anotação aviso ao spring que esta classe possui um endpoint (url que acessa uma API)
@RequestMapping("/api/courses") //Este é o endpoint que estará exposto na API
@AllArgsConstructor //Esta anotação informa ao spring que a injeção de dependências está sendo feita via construtor, isso é boa prática. Isso dispensa a anotação '@Autowired'
public class CourseController {

    //Este atributo está possibilitando que o spring faça a injeção de dependência deste repositório nesta Controller
    //O termo 'final' é para garantir que esta instância será utilizada sem nenhuma modificação em seus dados
    private final CourseRepository courseRepository;

    //Anotação para o método GET
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }

    //Anotação para o método POST
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course) {
        return courseRepository.save(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> buscarCurso(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(registroEncontrado -> ResponseEntity.ok().body(registroEncontrado))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> atualizaCurso(@Valid @PathVariable @NotNull @Positive Long id,
                                                @RequestBody @Valid Course course) {

        return courseRepository.findById(id)
                .map(registroEncontrado -> {
                    registroEncontrado.setName(course.getName());
                    registroEncontrado.setCategory(course.getCategory());
                    Course update = courseRepository.save(registroEncontrado);
                    return ResponseEntity.ok().body(update);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCurso(@PathVariable @NotNull @Positive Long id) {

        if(!courseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        courseRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
