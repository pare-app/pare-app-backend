package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassroomService extends AbstractEntityService<Classroom> {
    private final JpaRepository<Classroom,Integer> classroomRepository;

    @Override
    protected JpaRepository<Classroom, Integer> getRepository() {
        return classroomRepository;
    }
}
