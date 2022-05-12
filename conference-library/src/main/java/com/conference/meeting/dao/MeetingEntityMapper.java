package com.conference.meeting.dao;

import com.conference.exception.CustomException;
import com.conference.meeting.dto.MeetingRegisterDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.user.dao.UserEntity;

import java.time.*;
import java.time.format.DateTimeFormatter;

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

    public OffsetTime stringToOffsetTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime localTime = LocalTime.parse(time, formatter);
            return OffsetTime.of(localTime, ZoneId.of("Europe/Warsaw").getRules().getOffset(LocalDateTime.now()));
        } catch (Exception exception) {
            throw new CustomException("Nieprawidłowy format czasu. " +
                    "Prawidłowy pattern: HH:mm: " +
                    "przykład: 10:00", 500);
        }
    }
}
