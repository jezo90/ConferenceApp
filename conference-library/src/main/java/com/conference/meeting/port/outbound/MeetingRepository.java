package com.conference.meeting.port.outbound;

import com.conference.meeting.dto.MeetingRegisterDto;
import com.conference.meeting.dto.MeetingRequestDto;
import com.conference.meeting.dto.MeetingResponseDto;

import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

public interface MeetingRepository {
    List<MeetingResponseDto> getUserMeetings(String login);
    Optional<MeetingResponseDto> findByUsernameAndTime(String username, OffsetTime time);
    MeetingResponseDto registerMeeting(MeetingRegisterDto meetingRegisterDto, Long userId);
    Long countByTopicId(Long topicId);
}
