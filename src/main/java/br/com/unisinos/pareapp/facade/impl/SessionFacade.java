package br.com.unisinos.pareapp.facade.impl;

import br.com.unisinos.pareapp.model.dto.BaseEntityDto;
import br.com.unisinos.pareapp.model.dto.session.SessionCreationDto;
import br.com.unisinos.pareapp.model.dto.session.SessionEntityDto;
import br.com.unisinos.pareapp.model.dto.user.UserEntityDto;
import br.com.unisinos.pareapp.model.entity.Session;
import br.com.unisinos.pareapp.service.EntityService;
import br.com.unisinos.pareapp.service.impl.SessionService;
import com.github.roookeee.datus.api.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class SessionFacade extends AbstractEntityFacade<SessionEntityDto, Session> {
    private final SessionService service;
    private final Mapper<Session, SessionEntityDto> standardConverter;
    private final Mapper<SessionEntityDto, Session> inverseConverter;

    @Override
    protected EntityService<Session> getService() {
        return service;
    }

    @Override
    protected Mapper<Session, SessionEntityDto> getStandardConverter() {
        return standardConverter;
    }

    @Override
    protected Mapper<SessionEntityDto, Session> getInverseConverter() {
        return inverseConverter;
    }

    public SessionEntityDto getByExerciseAndPair(Integer exerciseId,Integer pairId){
        return standardConverter.convert(service.getByExerciseAndPair(exerciseId, pairId));
    }
}
