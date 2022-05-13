package com.conference.meeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingSpringRepository extends JpaRepository<MeetingEntity, Long> {
    List<MeetingEntity> findByUserEntityUsername(String login);
    Optional<MeetingEntity> findByUserEntityUsernameAndTime(String username, OffsetTime time);
    Long countByTime(OffsetTime offsetTime);
    Optional<MeetingEntity> findByUserEntityUsernameAndTopicId(String username, Long topicId);
}
