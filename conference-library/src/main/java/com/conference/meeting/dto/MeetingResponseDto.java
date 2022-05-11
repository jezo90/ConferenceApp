package com.conference.meeting.dto;

import java.time.OffsetTime;

public record MeetingResponseDto (
        OffsetTime time,
        Long topicId
){
}
