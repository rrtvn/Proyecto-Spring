package cl.nvrrt.cvseguro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.nvrrt.cvseguro.entities.TipoUser;
import cl.nvrrt.cvseguro.services.tipoUser.TipoUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins =  {"*"})
@RestController
@RequestMapping("/api/tipoUser")
public class TipoUserControllers {

    @Autowired
    private TipoUserService tipoUserService;

    @GetMapping("/get")
    public List<TipoUser> getAll() {
        //LLAMA A METODO GET ALL  DEL SERVICIO Y LO RETORNA
        return tipoUserService.getAll();
    }

    @GetMapping("/findName")
    public TipoUser getByTipoUser(@RequestParam String name) {
        
        return tipoUserService.findByName(name);
    }

    @PostMapping("/post")
    public TipoUser post (@RequestBody TipoUser tipoUser) {
        //LLAMA A METODO SAVA DENTRO DEL SERVICE PARA 
        //PROCESAR EL REGISTRO        
        return tipoUserService.save(tipoUser);
    }
    
    
    
}
