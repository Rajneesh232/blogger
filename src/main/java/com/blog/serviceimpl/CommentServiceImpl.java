package com.blog.serviceimpl;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository,CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository=commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post not found with id:"+postId));

       Comment comment= new Comment();
//        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPost(post);

        Comment savedComment =  commentRepository.save(comment);

        CommentDto dto=new CommentDto();
        dto.setId(savedComment.getId());
        dto.setBody(savedComment.getBody());
        dto.setEmail(savedComment.getEmail());
//        dto.getName(savedComment.setName());

        return dto;
    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Resource not foud with given id !!"+commentId));
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
       List<Comment> comments = commentRepository.findByPostId(postId);
       List<CommentDto>commentDtos= comments.stream().map(c->mapToDto(c)).collect(Collectors.toList());
   return commentDtos;
    }

    @Override
    public List<CommentDto> getAllComment() {
       List<Comment>comments= commentRepository.findAll();
        List<CommentDto>dtos=comments.stream().map(c->mapToDto(c)).collect(Collectors.toList());
        return dtos;
    }


    CommentDto mapToDto(Comment comment){
        CommentDto dto=new CommentDto();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());
//        dto.getName(comment.setName());
        return  dto;
    }
}
