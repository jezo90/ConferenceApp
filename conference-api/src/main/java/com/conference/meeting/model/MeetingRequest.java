package com.conference.meeting.model;

public record MeetingRequest(
        String username,
        String email,
        String time,
        Long topicId
) {
}
