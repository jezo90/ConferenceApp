package com.conference.meeting;

import com.conference.exception.CustomException;
import com.conference.meeting.dao.MeetingEntityMapper;
import com.conference.meeting.dao.MeetingSaveToFile;
import com.conference.meeting.dto.MeetingDto;
import com.conference.meeting.dto.MeetingRequestDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.meeting.port.outbound.MeetingRepository;
import com.conference.user.port.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
class MeetingService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final MeetingEntityMapper meetingEntityMapper = new MeetingEntityMapper();
    private final MeetingSaveToFile meetingSaveToFile = new MeetingSaveToFile();

    public List<MeetingResponseDto> getUserMeetings(String login) {

        List<MeetingResponseDto> meetingResponseDtoList = meetingRepository.getUserMeetings(login);

        if (meetingResponseDtoList.isEmpty())
            throw new CustomException("Użytkownik o podanym loginie nie bierze udział w żadnej prelekcji", 500);


        return meetingResponseDtoList;
    }

    public MeetingResponseDto registerMeeting(MeetingRequestDto meetingRequestDto) {

        Long userId = getUserIdIfExist(meetingRequestDto.username(),
                meetingRequestDto.email());

        MeetingDto meetingDto = new MeetingDto(
                userId,
                meetingRequestDto.username(),
                meetingRequestDto.email(),
                meetingEntityMapper.topicToTime(meetingRequestDto.topicId()),
                meetingRequestDto.topicId());

        checkIfCanParticipate(meetingDto.username(),
                meetingDto.time());

        checkIfFreeSpot(meetingDto.topicId());

        meetingSaveToFile.meetingDtoToFile(meetingDto);

        return meetingRepository.registerMeeting(meetingDto);
    }

    private Long getUserIdIfExist(String username, String email) {
        return userRepository.findIdByUsernameAndEmail(username, email)
                .orElseThrow(() -> new CustomException("Użytkownik o loginie " + username +
                        " nie jest powiązany z emailem " + email, 500));
    }

    private void checkIfCanParticipate(String username, OffsetTime time) {
        if (meetingRepository.findByUsernameAndTime(username, time).isPresent())
            throw new CustomException("Użytkownik o loginie " + username +
                    " jest już zapisany na prelekcję o godzinie " + time.format(DateTimeFormatter.ofPattern("HH:mm")), 500);
    }

    private void checkIfFreeSpot(Long topicId) {
        if (meetingRepository.countByTopicId(topicId) >= 5)
            throw new CustomException("Brak wolnych miejsc na tę ścieżkę tematyczną", 500);
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


}
