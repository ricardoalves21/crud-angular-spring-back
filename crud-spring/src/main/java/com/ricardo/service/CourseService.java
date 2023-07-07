package com.ricardo.service;

import com.ricardo.dto.CourseDTO;
import com.ricardo.dto.mapper.CourseMapper;
import com.ricardo.exception.RecordNotFoundException;
import com.ricardo.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    //Este atributo está possibilitando que o spring faça a injeção de dependência deste repositório nesta Controller
    //O termo 'final' é para garantir que esta instância será utilizada sem nenhuma modificação em seus dados
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO buscarCurso(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow( () -> new RecordNotFoundException(id) );
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO atualizaCurso(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
                .map(registroEncontrado -> {
                    registroEncontrado.setName(course.name());
                    registroEncontrado.setCategory(courseMapper.convertCategoryValue(course.category()));
                    return courseMapper.toDTO(courseRepository.save(registroEncontrado));
                }).orElseThrow( () -> new RecordNotFoundException(id) );
    }

    public void removeCurso(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
