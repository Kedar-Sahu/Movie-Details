package in.main.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.main.entity.Movie;
import in.main.service.MovieService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/admin/")
public class AdminController {

	@Autowired
	private MovieService movieService;
	
	
	@GetMapping("/createMovie")
	public String createMovie(Model model) {
		List<Movie> movieList=movieService.getAllMovie();
       	model.addAttribute("movieList", movieList);
		return "admin/createMovie";
	}
	
	
	@PostMapping("/saveMovie")
    public String savePost(@ModelAttribute Movie movie,@RequestParam("file") MultipartFile file,HttpSession session) throws IOException {
    	
    	String posterName=file!=null ? file.getOriginalFilename() : "default.jpg";
		movie.setPoster( posterName);
		
		if(movieService.existMovie(movie.getName())) {
			session.setAttribute("errorMsg","movie name already exists");
		}
		else {
			Movie saveMovie=movieService.createMovie(movie);
			if(ObjectUtils.isEmpty(saveMovie)) {
				session.setAttribute("errorMsg","Not saved ! internal server error");
			}
			else {
				File saveFile=new ClassPathResource("/static").getFile();
				Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+"images"+File.separator+file.getOriginalFilename());
				
				Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				
				session.setAttribute("succMsg","Movie Saved successfully");
			}
		}
    	
    	return "redirect:/api/admin/createMovie";
    }
	
	
	@GetMapping("/loadUpdateMovie/{movieId}")
   	public String updatePost(Model model,@PathVariable Long movieId) {
    	Movie movie=movieService.getMovieById(movieId);
    	model.addAttribute("m", movie);
   		return "admin/updateMovie";
   	}
	
	
	 @PostMapping("/updateMovie")
		public String updateCategory(@ModelAttribute Movie movie,@RequestParam("file") MultipartFile file,HttpSession session) throws IOException {
			
			Movie oldMovie=movieService.getMovieById(movie.getMovieId());
			String PosterName=file.isEmpty() ?  oldMovie.getPoster() : file.getOriginalFilename();
			oldMovie.setPoster(PosterName);
			
			Movie updateMovie=movieService.updateMovie(movie.getMovieId(), oldMovie);
			if(!ObjectUtils.isEmpty(updateMovie)) {
				
				if(!file.isEmpty()) {
					File saveFile=new ClassPathResource("static").getFile();
					Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+"images"+File.separator+file.getOriginalFilename());
					
					Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				}
				session.setAttribute("succMsg","Movie Update Successfully");
			}
			else {
				session.setAttribute("errorMsg","Something Is Wrong On Server");
			}
			return "redirect:/api/admin/loadUpdateMovie/" + movie.getMovieId();
		}
	    
	
	 @GetMapping("/deleteMovie/{movieId}")
	    public String deletePst(@PathVariable Long movieId,HttpSession session) {
	    	Boolean delete =movieService.deleteMovie(movieId);
			if(delete) {
				session.setAttribute("succMsg","Movie Delete Successfully");
				return "redirect:/api/admin/createMovie";
			}
			else {
				session.setAttribute("errorMsg","Something Wrong On Server");
				return "redirect:/api/admin/createMovie";
			}
	    }
	 
	 
}
