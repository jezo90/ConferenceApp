package com.conference.meeting.dao;

import com.conference.exception.CustomException;
import com.conference.meeting.dto.MeetingRegisterDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.user.dao.UserEntity;

import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class MeetingEntityMapper {
    public MeetingResponseDto map(MeetingEntity meetingEntity) {
        return new MeetingResponseDto(
                meetingEntity.getTime(),
                meetingEntity.getTopicId()
        );
    }

    public MeetingEntity map(MeetingRegisterDto meetingRegisterDto, Long userId) {
        MeetingEntity meetingEntity = new MeetingEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        meetingEntity.setUserEntity(userEntity);
        meetingEntity.setTime(meetingRegisterDto.time());
        meetingEntity.setTopicId(meetingRegisterDto.topicId());

        return meetingEntity;
    }

    public MeetingEntity map(Long topicId) {
        MeetingEntity meetingEntity = new MeetingEntity();
        meetingEntity.setTopicId(topicId);

        return meetingEntity;
    }


    public OffsetTime topicToTime(Long topicId) {
        ZoneOffset zoneOffset = ZoneId.of("Europe/Warsaw").getRules().getOffset(LocalDateTime.now());

        if (topicId >= 1 && topicId <= 3)
            return OffsetTime.of(10, 0, 0, 0, zoneOffset);
        if (topicId >= 4 && topicId <= 6)
            return OffsetTime.of(12, 0, 0, 0, zoneOffset);
        if (topicId >= 7 && topicId <= 9)
            return OffsetTime.of(14, 0, 0, 0, zoneOffset);

        throw new CustomException("Nieprawidłowy numer prelekcji. ", 500);
    }
}
