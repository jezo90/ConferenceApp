package com.conference.meeting.model;

import java.time.OffsetTime;

public record MeetingResponse (
        OffsetTime time,
        String topic
){
}