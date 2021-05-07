package per.desafiob2w.planetas.Servicos;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import per.desafiob2w.planetas.Modelos.JsonTemplates.DadosPlanetaTemplate;
import per.desafiob2w.planetas.Modelos.JsonTemplates.RespostaConsultaPlanetasTemplate;

/*
 * Classe de serviço responsável por obter dados da API pública do Star Wars.
 * 
 * Author: Matheus Silva
*/
@Service
public class ConexaoApiStarWarsServico {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private static ObjectMapper mapper = new ObjectMapper();

    private HttpClient httpClient = HttpClientBuilder.create().build();

    public Integer getTotalAparicoesDoPlaneta(String nomePlaneta){
        LOG.info("Iniciando consulta por total de aparições do planeta {}", nomePlaneta);
        try{
            String url = configurarUrlChamadaAPI(nomePlaneta);
            
            HttpResponse response = httpClient.execute(new HttpGet(url));
            String responseString = EntityUtils.toString(response.getEntity());

            RespostaConsultaPlanetasTemplate respostaConsultaConvertida = this.converterChamadaApiStarWars(responseString);
            
            if(respostaConsultaConvertida.getCount() == 1){
                    DadosPlanetaTemplate template = respostaConsultaConvertida.getResults().stream().findFirst().orElse(null);
                    if(template.getName().equals(nomePlaneta)){
                        Integer aparicoes = template.getFilms().size();
                        LOG.info("Número de aparições obtido para o planeta {}: {} ", nomePlaneta, aparicoes);
                        return aparicoes;
                    }
            }
            
            LOG.info("Planeta não encontrado na API!");
            return null;
        } catch(Exception e) {
            LOG.error("Falha na consulta a API");
            e.printStackTrace();
            return null;
        }
    }

    private String configurarUrlChamadaAPI(String nomePlaneta){
        nomePlaneta = nomePlaneta.replaceAll(" ", "%20");
        return "http://swapi.dev/api/planets/?search=" + nomePlaneta;
    }

    private RespostaConsultaPlanetasTemplate converterChamadaApiStarWars(String respostaJson){
        LOG.info("Iniciando tentativa de conversão de resposta.");
        RespostaConsultaPlanetasTemplate respostaConvertida;

        try{
            respostaConvertida = mapper.readValue(respostaJson, RespostaConsultaPlanetasTemplate.class);
            LOG.info("Conversão de resposta bem sucedida!");
            return respostaConvertida;
        } catch(Exception e){
            LOG.error("Erro no processamento de conversão de JSON");
            e.printStackTrace();
            return null;
        }
    }


}
