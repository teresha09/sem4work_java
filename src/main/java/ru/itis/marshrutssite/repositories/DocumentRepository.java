package ru.itis.marshrutssite.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.marshrutssite.models.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {
}
