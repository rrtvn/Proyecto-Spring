package cl.nvrrt.cvseguro.services.tipoUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nvrrt.cvseguro.entities.TipoUser;
import cl.nvrrt.cvseguro.repositories.TipoUserRepository;

@Service
public class TipoUserServiceImpl  implements TipoUserService{

    @Autowired
    private TipoUserRepository tipoUserRepo;
    
    @Override
    public TipoUserService save(TipoUser tipoUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public List<TipoUser> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
