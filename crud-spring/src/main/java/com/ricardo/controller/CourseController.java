package com.ricardo.controller;


import com.ricardo.dto.CourseDTO;
import com.ricardo.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
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
    public List<CourseDTO> list() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public CourseDTO buscarCurso(@PathVariable @NotNull @Positive Long id) {
        return courseService.buscarCurso(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public CourseDTO atualizaCurso(@PathVariable @NotNull @Positive Long id,
                                                @RequestBody @Valid @NotNull CourseDTO course) {
        return courseService.atualizaCurso(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeCurso(@PathVariable @NotNull @Positive Long id) {
        courseService.removeCurso(id);
    }

}
