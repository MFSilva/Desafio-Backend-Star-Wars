package per.desafiob2w.planetas.Servicos;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.desafiob2w.planetas.Modelos.Planeta;
import per.desafiob2w.planetas.Repositorio.PlanetaRepositorio;
/*
 * Classe de serviço responsável por operações que envolvem planetas
 * 
 * Author: Matheus Silva
*/
@Service
public class PlanetasServico {
    @Autowired 
    private ConexaoApiStarWarsServico conexaoApiStarWarsServico;

    @Autowired
    private PlanetaRepositorio repositorio;

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    

    public boolean cadastrarPlaneta(Planeta planeta){
        if(!this.isPlanetaJaCadastrado(planeta.getNome())){
            Integer respostaAPI = conexaoApiStarWarsServico.getTotalAparicoesDoPlaneta(planeta.getNome());
            planeta.setAparicoesEmFilmes(respostaAPI);
    
            Planeta planetaSalvo = repositorio.save(planeta);
    
            LOG.info("Planeta adicionado com sucesso! ID obtido {}", planetaSalvo.getId());

            return true;
        } else {
            LOG.info("Planeta já cadastrado!");
            return false;
        }
    }

    public boolean excluirPlaneta(String idPlaneta){
        repositorio.deleteById(idPlaneta);

        if(repositorio.findById(idPlaneta).orElse(null) == null){
            LOG.info("Planeta com id {} foi removido.", idPlaneta);
            return true;
        }
        return false;
    }

    public Planeta buscarPlanetaPorId(String idPlaneta){
        Planeta planeta = repositorio.findById(idPlaneta).orElse(null);
        if(planeta != null){
            return planeta;
        }
        LOG.info("Planeta com id {} não encontrado", idPlaneta);
        return null;
    }

    public Collection<Planeta> buscarTodosPlanetas(){
        Collection<Planeta> planetas = repositorio.findAll();
        if(planetas != null){
            return planetas;
        }
        LOG.info("Nenhum planeta encontrado");
        return null;
    }
    
    public Collection<Planeta> buscarPlanetaPorNome(String busca){
        Collection<Planeta> planetas = repositorio.findByNomeRegex(busca);
        if(planetas != null){
            return planetas;
        }
        LOG.info("Nenhum planeta que atenda ao critério de nome: {}", busca);
        return null;
    }

    private boolean isPlanetaJaCadastrado(String nomePlaneta){
        return this.repositorio.findByNomeRegex(nomePlaneta)
                        .stream()
                        .anyMatch(planeta -> planeta.getNome().equals(nomePlaneta));
    }
}
