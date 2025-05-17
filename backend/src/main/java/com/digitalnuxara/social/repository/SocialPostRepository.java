package com.digitalnuxara.social.repository;

import com.digitalnuxara.social.model.SocialPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SocialPostRepository extends JpaRepository<SocialPost, Long> {
    Page<SocialPost> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<SocialPost> findAllByOrderByLikesCountDesc(Pageable pageable);
    Page<SocialPost> findAllByOrderByCommentsCountDesc(Pageable pageable);
    Page<SocialPost> findByUserIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);
} 