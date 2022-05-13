package com.conference.meeting.dto;

import java.time.OffsetTime;

public record MeetingStatsDto(
        OffsetTime time,
        Long users,
        Double participation
)
{
}
