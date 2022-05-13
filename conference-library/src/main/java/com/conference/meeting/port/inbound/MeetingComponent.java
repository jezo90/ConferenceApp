package com.conference.meeting.port.inbound;

import com.conference.meeting.dto.MeetingRequestDto;
import com.conference.meeting.dto.MeetingResponseDto;

import java.util.List;

public interface MeetingComponent {
    List<MeetingResponseDto> getUserMeetings(String login);
    MeetingResponseDto registerMeeting(MeetingRequestDto meetingRequestDto);
    void removeMeeting(MeetingRequestDto meetingRequestDto);
}
