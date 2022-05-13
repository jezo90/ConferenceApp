package com.conference.meeting.dto;

import java.time.OffsetTime;

public record MeetingDetailsDto(
    OffsetTime time,
    Long topicId
){
}
