package com.conference.meeting;

import com.conference.meeting.dto.MeetingRequestDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.meeting.dto.MeetingStatsDto;
import com.conference.meeting.dto.MeetingTopicStatsDto;
import com.conference.meeting.port.inbound.MeetingComponent;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class MeetingFacade implements MeetingComponent {
    private final MeetingService meetingService;

    @Override
    public List<MeetingResponseDto> getUserMeetings(String login) {
        return meetingService.getUserMeetings(login);
    }

    @Override
    public MeetingResponseDto registerMeeting(MeetingRequestDto meetingRequestDto) {
        return meetingService.registerMeeting(meetingRequestDto);
    }

    @Override
    public void removeMeeting(MeetingRequestDto meetingRequestDto) {
        meetingService.removeMeeting(meetingRequestDto);
    }

    @Override
    public List<MeetingStatsDto> generateMeetingStats() {
        return meetingService.generateMeetingStats();
    }

    @Override
    public List<MeetingTopicStatsDto> generateTopicStats() {
        return meetingService.generateTopicStats();
    }
}
