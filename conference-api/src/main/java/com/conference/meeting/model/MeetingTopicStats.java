package com.conference.meeting.model;

public record MeetingTopicStats(
        Long users,
        Long topicId,
        Double participation
) {
}
