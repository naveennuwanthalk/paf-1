package com.digitalnuxara.social.controller;

import com.digitalnuxara.social.model.SocialPost;
import com.digitalnuxara.social.service.SocialPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/social")
@CrossOrigin(origins = "*")
public class SocialPostController {
    
    @Autowired
    private SocialPostService socialPostService;

    @GetMapping("/posts")
    public ResponseEntity<Page<SocialPost>> getPosts(
            @RequestParam(defaultValue = "recent") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(socialPostService.getPosts(sortBy, PageRequest.of(page, size)));
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<Page<SocialPost>> getUserPosts(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(socialPostService.getUserPosts(userId, PageRequest.of(page, size)));
    }

    @PostMapping("/posts")
    public ResponseEntity<SocialPost> createPost(@RequestBody SocialPost post) {
        return ResponseEntity.ok(socialPostService.createPost(post));
    }

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<Void> incrementLikes(@PathVariable Long postId) {
        socialPostService.incrementLikes(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<Void> incrementComments(@PathVariable Long postId) {
        socialPostService.incrementComments(postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        socialPostService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
} 