package com.conference.meeting.dao;

import com.conference.meeting.dto.MeetingRegisterDto;
import com.conference.meeting.dto.MeetingRequestDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.meeting.port.outbound.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class MeetingAdapter implements MeetingRepository {
    private final MeetingSpringRepository meetingSpringRepository;
    private final MeetingEntityMapper meetingEntityMapper = new MeetingEntityMapper();

    @Override
    public List<MeetingResponseDto> getUserMeetings(String login) {
        return meetingSpringRepository.findByUserEntityUsername(login)
                .stream().map(meetingEntityMapper::map).toList();
    }

    @Override
    public Optional<MeetingResponseDto> findByUsernameAndTime(String username, OffsetTime time) {
        return meetingSpringRepository.findByUserEntityUsernameAndTime(username, time)
                .map(meetingEntityMapper::map);
    }

    @Override
    public MeetingResponseDto registerMeeting(MeetingRegisterDto meetingRegisterDto, Long userId) {
        return meetingEntityMapper.map(
                meetingSpringRepository.save(
                        meetingEntityMapper.map(meetingRegisterDto, userId)));
    }

    @Override
    public Long countByTopicId(Long topicId) {
        return meetingSpringRepository.countByTopicId(topicId);
    }
}
