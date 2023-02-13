package project.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.com.models.Book;
import project.com.models.Client;
import project.com.repositories.BookRepository;
import project.com.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ClientService {
    BookRepository bookRepository;
    ClientRepository clientRepository;

    @Autowired
    public ClientService(BookRepository bookRepository, ClientRepository clientRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClient(int id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    @Transactional
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Transactional
    public void update(int id, Client updClient) {
        updClient.setId(id);
        clientRepository.save(updClient);
    }

    @Transactional
    public void delete(int id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public List<Book> checkBooks(int id) {
        Client client = getClient(id);
        return bookRepository.findByOwner(client);
    }
}
