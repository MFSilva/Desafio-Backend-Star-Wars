package per.desafiob2w.planetas.Modelos.JsonTemplates;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe que representa o resultado de uma consulta a API do Star Wars após o json ser convertido.
 * 
 * @author: Matheus Silva
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaConsultaPlanetasTemplate {
    private int count;
    private String next;
    private String previous;
    private Collection<DadosPlanetaTemplate> results;
}
