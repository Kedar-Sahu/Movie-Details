package in.main.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Movie {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long movieId;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Integer releaseYear;
	
	@Column(nullable=false)
	private String director;
	
	@Column(nullable=false)
	private String movieCast;
	
	@Column(nullable=false)
	private String studio;
	
	@Column(nullable=false)
	private String budget;
	
	@Column(nullable=false)
	private String Collection;
	
	@Column(nullable=false)
	private String poster;
	
	@Column(nullable=false)
	private String rating;
	
	
}
