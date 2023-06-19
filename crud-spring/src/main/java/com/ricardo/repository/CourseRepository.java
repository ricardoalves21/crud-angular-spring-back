package com.ricardo.repository;

import com.ricardo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Esta anotação avisa ao spring que este repositório fará acessos ao DB.
//Este repositório usará todas as interfaces disponíveis no 'spring data jpa' para acesso ao DB.
//Com isso temos acessos a métodos como 'findById', 'findByName', 'findAll', etc.
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
