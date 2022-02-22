package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.dao.BaseDao;
import br.com.unisinos.pareapp.model.entity.Exercise;
import br.com.unisinos.pareapp.model.entity.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultSessionService extends AbstractEntityService<Session> {
    private final BaseDao<Session> sessionDao;

    @Override
    protected BaseDao<Session> getDao() {
        return sessionDao;
    }
}
