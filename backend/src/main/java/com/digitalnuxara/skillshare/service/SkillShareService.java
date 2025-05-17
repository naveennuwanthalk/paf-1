package com.digitalnuxara.skillshare.service;

import com.digitalnuxara.skillshare.model.SkillShare;
import com.digitalnuxara.skillshare.repository.SkillShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class SkillShareService {
    
    @Autowired
    private SkillShareRepository skillShareRepository;

    public Page<SkillShare> getAllSkillShares(Pageable pageable) {
        return skillShareRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Page<SkillShare> getSkillSharesByLevel(String skillLevel, Pageable pageable) {
        return skillShareRepository.findBySkillLevel(skillLevel, pageable);
    }

    public Page<SkillShare> getSkillSharesByCategory(String category, Pageable pageable) {
        return skillShareRepository.findByCategory(category, pageable);
    }

    public Page<SkillShare> getUserSkillShares(UUID userId, Pageable pageable) {
        return skillShareRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
    }

    public List<SkillShare> searchSkillShares(String query) {
        return skillShareRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    @Transactional
    public SkillShare createSkillShare(SkillShare skillShare) {
        return skillShareRepository.save(skillShare);
    }

    @Transactional
    public void deleteSkillShare(Long id) {
        skillShareRepository.deleteById(id);
    }

    @Transactional
    public SkillShare updateSkillShare(Long id, SkillShare updatedSkillShare) {
        return skillShareRepository.findById(id)
            .map(skillShare -> {
                skillShare.setTitle(updatedSkillShare.getTitle());
                skillShare.setDescription(updatedSkillShare.getDescription());
                skillShare.setSkillLevel(updatedSkillShare.getSkillLevel());
                skillShare.setCategory(updatedSkillShare.getCategory());
                skillShare.setDuration(updatedSkillShare.getDuration());
                skillShare.setMediaUrls(updatedSkillShare.getMediaUrls());
                return skillShareRepository.save(skillShare);
            })
            .orElseThrow(() -> new RuntimeException("SkillShare not found"));
    }
} 