package com.conference.meeting.dto;

import java.time.OffsetTime;

public record MeetingRegisterDto(
        String username,
        String email,
        OffsetTime time,
        Long topicId
) {
}