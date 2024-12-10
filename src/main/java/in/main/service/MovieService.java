package in.main.service;

import java.util.List;
import in.main.entity.Movie;


public interface MovieService {

	Movie createMovie(Movie movie);
	
	Movie getMovieById(Long movieId);
	
	List<Movie> getAllMovie();
	
	Movie updateMovie(Long movieId,Movie movie);
	
	Boolean deleteMovie(Long movieId);
	
    Boolean existMovie(String name);
    
    List<Movie> searchByName(String name);
	
}
