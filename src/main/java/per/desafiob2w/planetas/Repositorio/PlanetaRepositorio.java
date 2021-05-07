package per.desafiob2w.planetas.Repositorio;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import per.desafiob2w.planetas.Modelos.Planeta;

/**
 * Repositório de Planetas.
 * Maneja consultas e operações no MongoDB
 * 
 * @author: Matheus Silva
*/
@Repository
public interface PlanetaRepositorio extends MongoRepository<Planeta, String>{
	public Collection<Planeta> findByNomeRegex(String nome);
}