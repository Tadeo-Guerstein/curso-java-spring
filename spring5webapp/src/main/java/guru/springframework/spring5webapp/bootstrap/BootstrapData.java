package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in bootstrap");

        Publisher publisher = new Publisher();

        publisher.setName("Arg");
        publisher.setAddressLine1("Av Independencia 2456");
        publisher.setCity("Mar del plata");
        publisher.setState("Buenos Aires");
        publisher.setZip("7600");

        publisherRepository.save(publisher);

        System.out.println("Number of Publishers " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author jkr = new Author("JK", "Rowling");
        Book hp = new Book("Harry Potter", "1241234");

        hp.setPublisher(publisher);
        publisher.getBooks().add(hp);

        jkr.getBooks().add(hp);
        hp.getAuthors().add(jkr);

        authorRepository.save(jkr);
        bookRepository.save(hp);
        publisherRepository.save(publisher);

        System.out.println("Number of Books " + bookRepository.count());
        System.out.println("Publisher Number Of books " + publisher.getBooks().size());
    }
}
