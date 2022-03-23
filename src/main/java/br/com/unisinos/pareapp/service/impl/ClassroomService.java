package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.Classroom;
import br.com.unisinos.pareapp.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassroomService extends AbstractEntityService<Classroom> {
    private final JpaRepository<Classroom,Integer> classroomRepository;
    private final HttpSessionService httpSessionService;

    @Override
    protected JpaRepository<Classroom, Integer> getRepository() {
        return classroomRepository;
    }

    @Override
    protected void verifyAccessPermission(Classroom entity) {
        User loggedUser = httpSessionService.getLoggedUser();
        if(!entity.getStudents().contains(loggedUser))
            throw new AccessDeniedException("Access denied");
    }

    @Override
    protected void validateParameters(Classroom entity) {

    }
}
