package cloud.alexanderweb.residentes.helpers;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Component;

import cloud.alexanderweb.residentes.persistence.entities.Residente;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Component
public class ResidenteGenerator {
    
  public Residente newResidente( Residente residente ) {
    // generar id unico
    String uuid = UUID.randomUUID().toString();

    //encriptar password
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    String encryptedPassword = argon2.hash(
      10,
      1024,
      1,
      residente.getPassword()
    );

    Residente newResidente = new Residente(
      uuid,
      residente.getDni(),
      residente.getName(), 
      residente.getLastNames(), 
      residente.getEmail(), 
      encryptedPassword,
      false,
      LocalDateTime.now(),
      LocalDateTime.now()
    );

    return newResidente;
  }
    
}
