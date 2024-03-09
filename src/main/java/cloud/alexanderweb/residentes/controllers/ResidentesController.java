package cloud.alexanderweb.residentes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.alexanderweb.residentes.helpers.GenerateResponse;
import cloud.alexanderweb.residentes.helpers.ResidenteGenerator;
import cloud.alexanderweb.residentes.helpers.ResidenteValidations;
import cloud.alexanderweb.residentes.persistence.entities.Residente;
import cloud.alexanderweb.residentes.services.ResidenteService;


@RestController
@RequestMapping("/api-residentes/v1/residentes")
public class ResidentesController {

  @Autowired
  private ResidenteService residenteService;

  @Autowired
  private ResidenteValidations residenteValidations;

  @Autowired
  private GenerateResponse generateResponse;

  @Autowired
  public ResidenteGenerator residenteGenerator;

  @GetMapping()
  public ResponseEntity<?> getResidentes() {
    List<Residente> allResidentes = this.residenteService.getResidentes();
    return generateResponse.getResponse( allResidentes );
  }
  
  @PostMapping()
  public ResponseEntity<?> postResidente( @RequestBody Residente residente ) {

    List<String> residenteErrors = residenteValidations.isValidResidente(residente);

    if( !residenteErrors.isEmpty() ) 
      return generateResponse.getResponse( residenteErrors );

    Residente newResidente = residenteGenerator.newResidente( residente );
    Residente addedResidente = this.residenteService.postResidente( newResidente );

    if( addedResidente == null ) 
      return ResponseEntity.badRequest().build();
  
    return generateResponse.getResponse( addedResidente,  residenteErrors );
  }
}
