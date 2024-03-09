package cloud.alexanderweb.residentes.helpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GenerateResponse {
  
  @Value("${base_url}")
  private String base_url;

  public ResponseEntity<?> getResponse( List<String> errors ){
    return getResponse( Collections.emptyList(), errors );
  }

  public ResponseEntity<?> getResponse( Object results ){
    return getResponse( results, Collections.emptyList() );
  }

  public ResponseEntity<?> getResponse( Object results, List<String> errors ){

    Map<String, Object> response = new HashMap<>();
    List<?> resultsList;

    if( !errors.isEmpty() ){
      response.put("ok", false);
      response.put( "errors", errors ); 
      return ResponseEntity.badRequest().body(response);
    } 

    resultsList = (results instanceof List<?>)
      ? (List<?>) results
      : Arrays.asList(results);

    response.put("ok", true);
    response.put("total", resultsList.size());
    response.put("results", resultsList);
    return ResponseEntity.ok(response);
  }
}
