package per.desafiob2w.planetas.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe de domínio que representa um planeta.
 * 
 * @author: Matheus Silva
*/
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Document(collection = "planetas")
public class Planeta {
    @Id
    private String id;

    private String nome;

    private String[] clima;

    private String[] terreno;

    private Integer aparicoesEmFilmes;
}
