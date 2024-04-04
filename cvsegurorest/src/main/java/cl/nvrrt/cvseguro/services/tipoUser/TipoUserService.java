package cl.nvrrt.cvseguro.services.tipoUser;

import java.util.List;

import cl.nvrrt.cvseguro.entities.TipoUser;

public interface TipoUserService {

    TipoUser save(TipoUser  tipoUser);
    List<TipoUser> getAll();
    void delete(String id);    
    TipoUser findByName(String name);
    
}
