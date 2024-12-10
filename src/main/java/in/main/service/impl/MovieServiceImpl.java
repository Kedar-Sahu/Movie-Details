package in.main.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import in.main.entity.Movie;
import in.main.repository.MovieRepository;
import in.main.service.MovieService;
import jakarta.servlet.http.HttpSession;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Movie createMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public Movie getMovieById(Long movieId) {
		return  movieRepository.findById(movieId).get();
	}

	@Override
	public List<Movie> getAllMovie() {
		return movieRepository.findAll();
	}

	@Override
	public Movie updateMovie(Long movieId, Movie movie) {
		
		Movie movie1=movieRepository.findById(movieId).get();
		
		if(!ObjectUtils.isEmpty(movie1)) {
			movie1.setName(movie.getName());
			movie1.setReleaseYear(movie.getReleaseYear());
			movie1.setDirector(movie.getDirector());
			movie1.setMovieCast(movie.getMovieCast());
			movie1.setStudio(movie.getStudio());
			movie1.setBudget(movie.getBudget());
			movie1.setCollection(movie.getCollection());
			movie1.setPoster(movie.getPoster());
			
			return movieRepository.save(movie1); 
		}
		
		return null;
	}

	@Override
	public Boolean deleteMovie(Long movieId) {
		Movie movie=movieRepository.findById(movieId).get();
		
		if(!ObjectUtils.isEmpty(movie)) {
			movieRepository.delete(movie);
			return true;
		}
		return false;
	}


	@Override
	public Boolean existMovie(String name) {
		return movieRepository.existsByName(name);
	}

	@Override
	public List<Movie> searchByName(String name) {
		return movieRepository.findAllByNameContaining(name);
	}
	
	public void removeSessionMessage() {
		HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("succMsg");
		session.removeAttribute("errorMsg");
	}
}
