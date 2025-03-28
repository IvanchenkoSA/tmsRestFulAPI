package ru.isa.tmsystem.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.isa.tmsystem.model.Comment;
import ru.isa.tmsystem.service.CommentService;

@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/comment/create")
    public String addComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
        return comment.getAuthor() + " has been added to the list.";
    }

}
