package project.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.models.Book;
import project.com.models.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

}
