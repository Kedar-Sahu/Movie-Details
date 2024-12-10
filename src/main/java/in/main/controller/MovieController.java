package in.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.main.entity.Movie;
import in.main.service.MovieService;

@Controller
@RequestMapping("/api/client/")
public class MovieController {

	
	@Autowired
	private MovieService movieService;
	
	
	@GetMapping("/getAllMovies")
   	public String getAllMovies(Model model) {
       	List<Movie> movieList=movieService.getAllMovie();
       	model.addAttribute("movieList", movieList);
   		return "showAllMovies";
   	}
	
	@GetMapping("/getMovie/{movieId}")
	public String getMovie(Model model,@PathVariable Long movieId) {
		 Movie movie=movieService.getMovieById(movieId);
		 model.addAttribute("movie", movie);
		return "showMovie";
	}
	
	
	@PostMapping("/saveSearch")
    public String search(@RequestParam("name") String name, Model model) {
        List<Movie> movieList = movieService.searchByName(name);
        model.addAttribute("movieList", movieList);
        return "search"; 
    }
	
	
}
