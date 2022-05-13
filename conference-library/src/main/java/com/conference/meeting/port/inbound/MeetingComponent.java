package com.conference.meeting.port.inbound;

import com.conference.meeting.dto.MeetingRequestDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.meeting.dto.MeetingStatsDto;
import com.conference.meeting.dto.MeetingTopicStatsDto;

import java.util.List;

public interface MeetingComponent {
    List<MeetingResponseDto> getUserMeetings(String login);
    MeetingResponseDto registerMeeting(MeetingRequestDto meetingRequestDto);
    void removeMeeting(MeetingRequestDto meetingRequestDto);
    List<MeetingStatsDto> generateMeetingStats();
    List<MeetingTopicStatsDto> generateTopicStats();
}
