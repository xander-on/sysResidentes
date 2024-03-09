package cloud.alexanderweb.residentes.helpers;
import org.springframework.stereotype.Component;
import cloud.alexanderweb.residentes.persistence.entities.Residente;
import java.util.*;

@Component
public class ResidenteValidations {

  public List<String> isValidResidente( Residente residente ){
    List<String> residenteErrors = new ArrayList<>();
    
    residenteErrors.addAll( validatorDni( residente.getDni() ) );
      
    residenteErrors.addAll( validatorEmail( residente.getEmail() ) );

    residenteErrors.addAll( 
      validatorText( residente.getName(), "name" ) 
    );
    
    residenteErrors.addAll( 
      validatorText( residente.getLastNames(), "lastNames" ) 
    );
    
    //todo mas validaciones
    if( residente.getPassword() == null )
      residenteErrors.add("La contraseña es obligatoria");
    
    return residenteErrors;
  }


  private boolean hasValue( String value ){
    return value != null && !value.isEmpty();
  }

  private List<String> validatorText( String text, String fieldName ){
    List<String> errors = new ArrayList<>();

    if( !hasValue(text) ){
      errors.add("El campo " + fieldName + " es obligatorio");
      return errors;
    }

    if( text.length() < 3 )
      errors.add("El campo " + fieldName + " debe tener al menos 3 caracteres");

    if( !text.matches("[a-zA-Z]+") )
      errors.add("El campo " + fieldName + " debe ser solo texto");

    return errors;
  }

  private List<String> validatorDni( String dni ){

    List<String> errors = new ArrayList<>();

    if( dni == null || dni.isEmpty() ){
      errors.add("El dni es obligatorio");
      return errors;
    }

    if( dni.length() != 10 )
      errors.add("El dni debe tener 10 caracteres");

    if( !dni.matches("[0-9]+") )
      errors.add("El dni debe ser solo numeros");

    return errors;
  }


  private List<String> validatorEmail ( String email ){
    List<String> errors = new ArrayList<>();

    if( !hasValue(email) ){
      errors.add("El email es obligatorio");
      return errors;
    }

    if( !email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$") )
      errors.add("El email no es valido / ej: mycorreo@mail.com");

    return errors;
  }
}
