package com.digitalnuxara.skillshare.controller;

import com.digitalnuxara.skillshare.model.SkillShare;
import com.digitalnuxara.skillshare.service.SkillShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/skillshare")
@CrossOrigin(origins = "*")
public class SkillShareController {
    
    @Autowired
    private SkillShareService skillShareService;

    @GetMapping
    public ResponseEntity<Page<SkillShare>> getAllSkillShares(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(skillShareService.getAllSkillShares(PageRequest.of(page, size)));
    }

    @GetMapping("/level/{skillLevel}")
    public ResponseEntity<Page<SkillShare>> getSkillSharesByLevel(
            @PathVariable String skillLevel,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(skillShareService.getSkillSharesByLevel(skillLevel, PageRequest.of(page, size)));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<SkillShare>> getSkillSharesByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(skillShareService.getSkillSharesByCategory(category, PageRequest.of(page, size)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<SkillShare>> getUserSkillShares(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(skillShareService.getUserSkillShares(userId, PageRequest.of(page, size)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SkillShare>> searchSkillShares(@RequestParam String query) {
        return ResponseEntity.ok(skillShareService.searchSkillShares(query));
    }

    @PostMapping
    public ResponseEntity<SkillShare> createSkillShare(@RequestBody SkillShare skillShare) {
        return ResponseEntity.ok(skillShareService.createSkillShare(skillShare));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillShare> updateSkillShare(
            @PathVariable Long id,
            @RequestBody SkillShare skillShare) {
        return ResponseEntity.ok(skillShareService.updateSkillShare(id, skillShare));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkillShare(@PathVariable Long id) {
        skillShareService.deleteSkillShare(id);
        return ResponseEntity.ok().build();
    }
} 