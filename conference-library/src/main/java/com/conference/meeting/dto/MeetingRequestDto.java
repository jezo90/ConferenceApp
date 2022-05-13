package com.conference.meeting.dto;

public record MeetingRequestDto(
        String username,
        String email,
        Long topicId
) {
}
