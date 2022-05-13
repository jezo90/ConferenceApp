package com.conference.meeting.mapper;

import com.conference.meeting.dto.MeetingRequestDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.meeting.dto.MeetingStatsDto;
import com.conference.meeting.model.MeetingRequest;
import com.conference.meeting.model.MeetingResponse;
import com.conference.meeting.model.MeetingStats;

public class MeetingMapper {

    public MeetingResponse map(MeetingResponseDto meetingResponseDto)
    {
        return switch (meetingResponseDto.topicId().intValue()) {
            case 1 -> new MeetingResponse(meetingResponseDto.time(), "1.C++");
            case 2 -> new MeetingResponse(meetingResponseDto.time(), "2.C#");
            case 3 -> new MeetingResponse(meetingResponseDto.time(), "3.Java");
            case 4 -> new MeetingResponse(meetingResponseDto.time(), "4.MySQL");
            case 5 -> new MeetingResponse(meetingResponseDto.time(), "5.PostgreSQL");
            case 6 -> new MeetingResponse(meetingResponseDto.time(), "6.MSSQL");
            case 7 -> new MeetingResponse(meetingResponseDto.time(), "7.Facebook");
            case 8 -> new MeetingResponse(meetingResponseDto.time(), "8.Instagram");
            default -> new MeetingResponse(meetingResponseDto.time(), "9.Twitter");
        };
    }

    public MeetingRequestDto map(MeetingRequest meetingRequest)
    {
        return new MeetingRequestDto(
                meetingRequest.username(),
                meetingRequest.email(),
                meetingRequest.topicId()
        );
    }

    public MeetingStats map(MeetingStatsDto meetingStatsDto)
    {
        return new MeetingStats(
                meetingStatsDto.time(),
                meetingStatsDto.users(),
                meetingStatsDto.participation()
        );
    }

}
