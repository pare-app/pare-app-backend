package br.com.unisinos.pareapp.populator.impl.session;

import br.com.unisinos.pareapp.model.dto.session.SessionEntityDto;
import br.com.unisinos.pareapp.model.entity.Session;
import br.com.unisinos.pareapp.populator.EntityPopulator;
import org.springframework.stereotype.Component;

@Component
public class SessionEntityPopulator implements EntityPopulator<Session, SessionEntityDto> {
    @Override
    public Session inversePopulate(SessionEntityDto target) {
        return null;
    }

    @Override
    public SessionEntityDto populate(Session source) {
        return null;
    }
}
