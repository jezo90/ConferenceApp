package com.conference.meeting.port.outbound;

import com.conference.meeting.dto.MeetingDetailsDto;
import com.conference.meeting.dto.MeetingDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.meeting.dto.MeetingStatsDto;

import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

public interface MeetingRepository {
    List<MeetingResponseDto> getUserMeetings(String login);

    Optional<MeetingResponseDto> findByUsernameAndTime(String username, OffsetTime time);

    MeetingResponseDto registerMeeting(MeetingDto meetingDto);

    Long countByTime(OffsetTime offsetTime);

    Optional<Long> findIdByUsernameAndTopicId(String username, Long topicId);

    void removeMeeting(Long meetingId);

    List<MeetingDetailsDto> generateMeetingStats();
}
