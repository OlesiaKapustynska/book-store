package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book generationX = new Book();
            generationX.setTitle("Generation X");
            generationX.setAuthor("Douglas Coupland");
            generationX.setPrice(BigDecimal.valueOf(15));
            generationX.setIsbn("031205436X");
            generationX.setDescription(
                    "Generation X is Douglas Coupland's classic novel about the generation "
                            + "born from 1960 to 1978 ―a generation known until then simply as "
                            + "twenty somethings.");
            generationX.setCoverImage(
                    "https://upload.wikimedia.org/wikipedia/en/d/d0/GenerationX.jpg");

            Book generationA = new Book();
            generationA.setTitle("Generation A");
            generationA.setAuthor("Douglas Coupland");
            generationA.setPrice(BigDecimal.valueOf(17));
            generationA.setIsbn("978-0434019830");
            generationA.setDescription(
                    "Generation A is the thirteenth novel from Canadian novelist Douglas "
                            + "Coupland. It takes place in a near future, in a world in which "
                            + "bees have become extinct.");
            generationA.setCoverImage(
                    "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSP9dYYr2CnYy8E2U36iuxw3aZJqe4emkamyDqUjfY0A6IqLUNM");

            bookService.save(generationX);
            bookService.save(generationA);

            System.out.println(bookService.findAll());
        };
    }
}
