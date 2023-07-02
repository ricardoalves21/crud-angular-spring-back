package com.ricardo.controller;


import com.ricardo.model.Course;
import com.ricardo.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController //Esta anotação aviso ao spring que esta classe possui um endpoint (url que acessa uma API)
@RequestMapping("/api/courses") //Este é o endpoint que estará exposto na API
public class CourseController {

    //Estes atributos está possibilitando que o spring faça a injeção de dependências destas classes nesta Controller.
    //O termo 'final' é para garantir que esta instância será utilizada sem nenhuma modificação de dados.
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> list() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> buscarCurso(@PathVariable @NotNull @Positive Long id) {
        return courseService.buscarCurso(id)
                .map(registroEncontrado -> ResponseEntity.ok().body(registroEncontrado))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> atualizaCurso(@Valid @PathVariable @NotNull @Positive Long id,
                                                @RequestBody @Valid Course course) {

        return courseService.atualizaCurso(id, course)
                .map(registroEncontrado -> ResponseEntity.ok().body(registroEncontrado))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCurso(@PathVariable @NotNull @Positive Long id) {

        if(courseService.removeCurso(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
