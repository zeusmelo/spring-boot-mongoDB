package com.nelioalves.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.nelioalves.workshopmongo.domain.Post;
import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.AuthorDTO;
import com.nelioalves.workshopmongo.dto.CommentDTO;
import com.nelioalves.workshopmongo.repository.PostRepository;
import com.nelioalves.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User denise = new User(null, "Denise Pradella", "denise@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob,denise));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Viajarei para são paulo", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("11/04/2018"), "vacation time", "i'm so glad, god", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("good travel", sdf.parse("21/03/2023"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("enjoy", sdf.parse("28/03/2023"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("have a nice day", sdf.parse("21/01/2023"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		
		userRepository.save(maria);
		
	}

}
