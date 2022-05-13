package com.conference.meeting.dto;

import java.time.OffsetTime;

public record MeetingDto(
        Long userId,
        String username,
        String email,
        OffsetTime time,
        Long topicId
) {
}