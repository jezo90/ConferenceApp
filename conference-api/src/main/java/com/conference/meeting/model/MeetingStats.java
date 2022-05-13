package com.conference.meeting.model;

import java.time.OffsetTime;

public record MeetingStats(
        OffsetTime time,
        Long users,
        Double participation
)
{
}
