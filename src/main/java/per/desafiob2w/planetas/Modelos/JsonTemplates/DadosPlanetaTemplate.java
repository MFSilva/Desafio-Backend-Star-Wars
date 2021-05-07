package per.desafiob2w.planetas.Modelos.JsonTemplates;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe que representa fragmento dos detalhes de um planeta a ser convertido do json de resposta.
 * Utilizado internamente na classe {@link RespostaConsultaPlanetasTemplate} 
 * 
 * @author Matheus Silva
*/
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosPlanetaTemplate {
    private String name;
    private Collection<String> films;
}
