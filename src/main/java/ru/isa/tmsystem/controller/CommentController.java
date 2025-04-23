package ru.isa.tmsystem.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.dto.UserDTO;
import ru.isa.tmsystem.model.Comment;
import ru.isa.tmsystem.repository.CommentRepository;
import ru.isa.tmsystem.service.CommentService;

import java.util.List;



@Tag(name = "Comment controller")
@RestController
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Create comment with using userId", description = "Returns comment content")
    @PostMapping("/api/comment/create")
    public String createComment(@RequestBody UserDTO userDTO) {
        commentService.addComment(userDTO.content(), userDTO.userId());
        return "Comment " + userDTO.content() + " created";
    }

    @Operation(summary = "Get all comments", description = "Returns List<Comments>")
    @GetMapping("/api/comment/getAll")
    public List<Comment> getAllComments() {
        return commentService.getComments();
    }

    @Operation(summary = "Get all user comments By userId", description = "Returns all comments user")
    @GetMapping("/api/comment/getComment/{userId}")
    public ResponseEntity<List<Comment>> getComment(@PathVariable Long userId) {
        List<Comment> list = commentService.getCommentsByUserId(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "Update comment by id", description = "Update comment")
    @PutMapping("/api/comment/update/{id}")
    public void updateComment(@PathVariable Long id, @RequestParam String content) {
        commentService.updateComment(content, id);
    }

    @Operation(summary = "Delete all comments user by userId", description = "Remove all comments user")
    @DeleteMapping("/api/comment/deleteByUserId/{userId}")
    public void deleteComments(@PathVariable Long userId) {
        commentService.removeComments(userId);
    }

    @Operation(summary = "Delete comments by Id", description = "Remove comments by id")
    @DeleteMapping("/api/comment/deleteById/{Id}")
    public void deleteComment(@PathVariable Long Id) {
        commentService.removeComment(Id);
    }

}
