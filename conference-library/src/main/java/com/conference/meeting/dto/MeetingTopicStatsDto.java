package com.conference.meeting.dto;

public record MeetingTopicStatsDto(
        Long users,
        Long topicId,
        Double participation
) {
}
