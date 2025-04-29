package ru.isa.tmsystem.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.dto.CommentDTO;
import ru.isa.tmsystem.model.Comment;
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
    public Long createComment(@RequestBody CommentDTO commentDTO) {
        Long id = commentService.addComment(commentDTO.content(), commentDTO.userId());
        return id;
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

    @Operation(summary = "Delete comment by Id", description = "Remove comment by id")
    @DeleteMapping("/api/comment/deleteById/{Id}")
    public void deleteComment(@PathVariable Long Id) {
        commentService.removeComment(Id);
    }

}
