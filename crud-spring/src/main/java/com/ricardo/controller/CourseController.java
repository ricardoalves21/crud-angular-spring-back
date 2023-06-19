package com.ricardo.controller;

import com.ricardo.model.Course;
import com.ricardo.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

}
