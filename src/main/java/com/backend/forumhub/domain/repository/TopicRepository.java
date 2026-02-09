package com.backend.forumhub.domain.repository;

import com.backend.forumhub.domain.model.TopicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicModel, Long> {
    List<TopicModel> findAllByDeletedFalse();
    Optional<TopicModel> findByIdAndDeletedFalse(Long id);

}
