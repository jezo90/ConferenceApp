package com.conference.meeting;

import com.conference.exception.CustomException;
import com.conference.meeting.dao.MeetingEntityMapper;
import com.conference.meeting.dao.MeetingFileHandler;
import com.conference.meeting.dto.*;
import com.conference.meeting.port.outbound.MeetingRepository;
import com.conference.user.port.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class MeetingService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final MeetingEntityMapper meetingEntityMapper = new MeetingEntityMapper();
    private final MeetingFileHandler meetingFileHandler = new MeetingFileHandler();

    public List<MeetingResponseDto> getUserMeetings(String login) {

        List<MeetingResponseDto> meetingResponseDtoList = meetingRepository.getUserMeetings(login);

        if (meetingResponseDtoList.isEmpty())
            throw new CustomException("Użytkownik o podanym loginie nie bierze udział w żadnej prelekcji", 500);


        return meetingResponseDtoList;
    }

    public MeetingResponseDto registerMeeting(MeetingRequestDto meetingRequestDto) {

        Long userId = getUserIdIfExist(meetingRequestDto.username(),
                meetingRequestDto.email());

        MeetingDto meetingDto = createMeeting(userId, meetingRequestDto);

        checkIfCanParticipate(meetingDto.username(),
                meetingDto.time());

        checkIfFreeSpot(meetingDto.time());

        meetingFileHandler.saveToFile(meetingDto);

        return meetingRepository.registerMeeting(meetingDto);
    }

    private Long getUserIdIfExist(String username, String email) {
        return userRepository.findIdByUsernameAndEmail(username, email)
                .orElseThrow(() -> new CustomException("Użytkownik o loginie " + username +
                        " nie jest powiązany z emailem " + email, 500));
    }

    private MeetingDto createMeeting(Long userId, MeetingRequestDto meetingRequestDto)
    {
        return new MeetingDto(
                userId,
                meetingRequestDto.username(),
                meetingRequestDto.email(),
                meetingEntityMapper.topicToTime(meetingRequestDto.topicId()),
                meetingRequestDto.topicId());
    }


    private void checkIfCanParticipate(String username, OffsetTime time) {
        if (meetingRepository.findByUsernameAndTime(username, time).isPresent())
            throw new CustomException("Użytkownik o loginie " + username +
                    " jest już zapisany na prelekcję o godzinie " + time.format(DateTimeFormatter.ofPattern("HH:mm")), 500);
    }

    private void checkIfFreeSpot(OffsetTime offsetTime) {
        if (meetingRepository.countByTime(offsetTime) >= 5)
            throw new CustomException("Brak wolnych miejsc na tę prelekcję", 500);
    }

    public void removeMeeting(MeetingRequestDto meetingRequestDto) {

        getUserIdIfExist(meetingRequestDto.username(), meetingRequestDto.email());
        Long meetingId = getIdIfParticipate(meetingRequestDto.username(), meetingRequestDto.topicId());

        meetingRepository.removeMeeting(meetingId);
    }

    private Long getIdIfParticipate(String username, Long topicId)
    {
        return meetingRepository.findIdByUsernameAndTopicId(username, topicId)
                .orElseThrow(() -> new CustomException("Użytkownik o loginie " + username +
                        " nie jest zapisany prelekcję o id " + topicId, 500));
    }

    public List<MeetingStatsDto> generateMeetingStats() {
        return calculateMeetingStats(meetingRepository.generateMeetingStats(), generateTimes());
    }

    private List<OffsetTime> generateTimes()
    {
        ZoneOffset zoneOffset = ZoneId.of("Europe/Warsaw").getRules().getOffset(LocalDateTime.now());

        return Arrays.asList(
                OffsetTime.of(10,0,0,0,zoneOffset),
                OffsetTime.of(12,0,0,0,zoneOffset),
                OffsetTime.of(14,0,0,0,zoneOffset));
    }

    private List<MeetingStatsDto> calculateMeetingStats(List<MeetingDetailsDto> meetingDetailsDtoList, List<OffsetTime> times)
    {
        List<MeetingStatsDto> meetingStatsDtoList = new ArrayList<>();
        Long users;
        for (OffsetTime time: times) {
            users = meetingDetailsDtoList.stream()
                    .filter(meetingDetailsDto ->
                            meetingDetailsDto.time().equals(time)).count();
            meetingStatsDtoList.add(
                    new MeetingStatsDto(
                            time,
                            users,
                            users/5D*100)
            );
        }
        return meetingStatsDtoList;
    }

}
