package in.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.main.entity.Movie;


public interface MovieRepository extends JpaRepository<Movie,Long>{

    Boolean existsByName(String name);
	
	List<Movie> findAllByNameContaining(String name);
}
