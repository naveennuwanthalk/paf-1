package com.digitalnuxara.skillshare.repository;

import com.digitalnuxara.skillshare.model.SkillShare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface SkillShareRepository extends JpaRepository<SkillShare, Long> {
    Page<SkillShare> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<SkillShare> findBySkillLevel(String skillLevel, Pageable pageable);
    Page<SkillShare> findByCategory(String category, Pageable pageable);
    Page<SkillShare> findByUserIdOrderByCreatedAtDesc(UUID userId, Pageable pageable);
    List<SkillShare> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleQuery, String descriptionQuery);
} 