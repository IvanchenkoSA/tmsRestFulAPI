package ru.isa.tmsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.isa.tmsystem.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
