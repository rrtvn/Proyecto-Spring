package cl.nvrrt.cvseguro.services.tipoUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.nvrrt.cvseguro.entities.TipoUser;
import cl.nvrrt.cvseguro.repositories.TipoUserRepository;

@Service
public class TipoUserServiceImpl implements TipoUserService {

    //COMUNICACION CON MONGO

    @Autowired
    private TipoUserRepository tipoUserRepo;

    @Override
    public TipoUser save(TipoUser tipoUser) {
        //REGISTRAR TIPO DE USUARIO
        return tipoUserRepo.save(tipoUser);

    }

    @Override
    public List<TipoUser> getAll() {
        // OBTENER TIPOS DE USUARIO
        return tipoUserRepo.findAll();
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
