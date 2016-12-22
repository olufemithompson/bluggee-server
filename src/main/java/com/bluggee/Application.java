package com.bluggee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bluggee.repository.BlogCategoryRepository;
import com.bluggee.repository.BlogSourceRepository;
import com.bluggee.repository.ContentRepository;



@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    ContentRepository contentRepository;

    @Autowired
    BlogCategoryRepository blogCategoryRepository;
    
    
    @Autowired
    BlogSourceRepository blogSourceRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
	}

}
