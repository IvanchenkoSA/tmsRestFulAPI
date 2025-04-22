package ru.isa.tmsystem.service;

import org.springframework.stereotype.Service;
import ru.isa.tmsystem.model.Comment;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.repository.CommentRepository;
import ru.isa.tmsystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CommentService {

    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public CommentService(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public void addComment(String commentContent, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = new Comment(commentContent, user);
//        comment.setContent(commentContent);
//        comment.setUser(user);
        commentRepository.save(comment);
    }

    public void removeComments(Long userId) {
        List<Comment> list = getComments();
        list.stream().filter(comment -> comment.getUser().getId() != userId)
                .toList();
        list.removeIf(comment -> comment.getUser().getId() != userId);
        commentRepository.deleteAll(list);
    }

    public void removeComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }


    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getCommentsByUserId(Long userId) {
        List<Comment> list = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()) {
            if (comment.getUser().getId().equals(userId)) {
                list.add(comment);
            }
        }
        return list;
    }
}
