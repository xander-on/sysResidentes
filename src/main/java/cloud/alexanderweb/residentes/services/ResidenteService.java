package cloud.alexanderweb.residentes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.alexanderweb.residentes.persistence.entities.Residente;
import cloud.alexanderweb.residentes.repositories.ResidenteRepository;

@Service
public class ResidenteService {
    
    @Autowired
    private ResidenteRepository residenteRepository;

    public List<Residente> getResidentes(){
        return this.residenteRepository.findAll();
    }

    public Residente postResidente( Residente residente ){
        return this.residenteRepository.save( residente );
    }
}
