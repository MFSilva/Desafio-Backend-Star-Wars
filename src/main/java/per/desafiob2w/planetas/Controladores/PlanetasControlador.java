package per.desafiob2w.planetas.Controladores;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import per.desafiob2w.planetas.Modelos.Planeta;
import per.desafiob2w.planetas.Servicos.PlanetasServico;

/**
 * Classe de controle de requisições HTTP vindas do cliente.
 * 
 * @author: Matheus Silva
*/
@RestController
@RequestMapping("/planetas")
public class PlanetasControlador {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlanetasServico servico;

    //Gets
    @GetMapping("")
    public Collection<Planeta> getTodosPlanetas(){
        LOG.info("Consulta por todos os planetas");
        return servico.buscarTodosPlanetas();
    }

    @GetMapping("/{idPlaneta}/detalhes")
    public Planeta getPlanetaById(@PathVariable String idPlaneta){
        LOG.info("Exibindo detalhes do planeta com ID: {}", idPlaneta);
        return servico.buscarPlanetaPorId(idPlaneta);
    }

    @GetMapping(value = "/busca", params = "nome")
    public Collection<Planeta> getPlanetasByNome(@RequestParam("nome") String nome){
        LOG.info("Pesquisando por planetas que o nome contenha: {}", nome);
        return servico.buscarPlanetaPorNome(nome);
    }

    //Posts
    @PostMapping("/novo")
    public Map<String, String> novoPlaneta(@RequestBody Planeta planeta){
        LOG.info("Tentativa de cadastro de planeta: {}", planeta);
        Map<String, String> resposta = new HashMap<String, String>();
        
        if(servico.cadastrarPlaneta(planeta)){
            resposta.put("situacao", "sucesso");
            resposta.put("mensagem", "Planeta cadastrado com sucesso!");
        } else {
            resposta.put("situacao", "falha");
            resposta.put("motivo", "Planeta já cadastrado");
        }
        
        return resposta;
    }

    @PostMapping("/{idPlaneta}/excluir")
    public Map<String, String> removerPlaneta(@PathVariable String idPlaneta){
        LOG.info("Tentativa de exclusão de planeta com ID: {}", idPlaneta);
        Map<String, String> resposta = new HashMap<String, String>();

        if(servico.excluirPlaneta(idPlaneta)){
            resposta.put("situacao", "sucesso");
            resposta.put("mensagem", "O planeta foi excluído!");
        } else {
            resposta.put("situacao", "falha");
            resposta.put("motivo", "Planeta não foi excluído");
        }

        return resposta;
    }
}
