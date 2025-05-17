package com.digitalnuxara.social.service;

import com.digitalnuxara.social.model.SocialPost;
import com.digitalnuxara.social.repository.SocialPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class SocialPostService {
    
    @Autowired
    private SocialPostRepository socialPostRepository;

    public Page<SocialPost> getPosts(String sortBy, Pageable pageable) {
        switch (sortBy.toLowerCase()) {
            case "popular":
                return socialPostRepository.findAllByOrderByLikesCountDesc(pageable);
            case "discussed":
                return socialPostRepository.findAllByOrderByCommentsCountDesc(pageable);
            default:
                return socialPostRepository.findAllByOrderByCreatedAtDesc(pageable);
        }
    }

    public Page<SocialPost> getUserPosts(UUID userId, Pageable pageable) {
        return socialPostRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
    }

    @Transactional
    public SocialPost createPost(SocialPost post) {
        return socialPostRepository.save(post);
    }

    @Transactional
    public void incrementLikes(Long postId) {
        socialPostRepository.findById(postId).ifPresent(post -> {
            post.setLikesCount(post.getLikesCount() + 1);
            socialPostRepository.save(post);
        });
    }

    @Transactional
    public void incrementComments(Long postId) {
        socialPostRepository.findById(postId).ifPresent(post -> {
            post.setCommentsCount(post.getCommentsCount() + 1);
            socialPostRepository.save(post);
        });
    }

    @Transactional
    public void deletePost(Long postId) {
        socialPostRepository.deleteById(postId);
    }
} 