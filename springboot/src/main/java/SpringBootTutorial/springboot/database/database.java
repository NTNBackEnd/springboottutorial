package SpringBootTutorial.springboot.database;

import SpringBootTutorial.springboot.model.Product;
import SpringBootTutorial.springboot.repositories.repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class database {

    private static final Logger logger = LoggerFactory.getLogger(database.class);
    @Bean
    CommandLineRunner initDatabase(repository repositorie){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product p1 =  new Product("Iphone",2012,"");
                Product p2 =  new Product("Sam Sung",2014,"");
                logger.info("insert data: " + repositorie.save(p1));
                logger.info("insert data: " + repositorie.save(p2));
            }
        };
    }
}
