package ru.isa.tmsystem.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.dto.UserDTO;
import ru.isa.tmsystem.model.Comment;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.repository.CommentRepository;
import ru.isa.tmsystem.service.CommentService;
import ru.isa.tmsystem.service.UserService;

import java.util.List;

@RestController
public class CommentController {


    private final CommentRepository commentRepository;
    private CommentService commentService;
    private UserService userService;

    public CommentController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }


    @PostMapping("/api/comment/create")
    public String createComment(@RequestBody UserDTO userDTO) {
        commentService.addComment(userDTO.content(), userDTO.userId());
        return "Comment " + userDTO.content() + " created";
    }

    @GetMapping("/api/comment/getAll")
    public List<Comment> getAllComments() {
        return commentService.getComments();
    }

    @GetMapping("/api/comment/getComment/{userId}")
    public ResponseEntity<List<Comment>> getComment(@PathVariable Long userId) {
        List<Comment> list = commentService.getCommentsByUserId(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/api/comment/deleteByUserId/{userId}")
    public void deleteComments(@PathVariable Long userId) {
        commentService.removeComments(userId);
    }

    @DeleteMapping("/api/comment/deleteById/{Id}")
    public void deleteComment(@PathVariable Long Id) {
        commentService.removeComment(Id);
    }

}
