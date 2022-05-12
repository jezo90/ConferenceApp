package com.conference.meeting;

import com.conference.exception.CustomException;
import com.conference.meeting.dao.MeetingEntityMapper;
import com.conference.meeting.dao.MeetingSaveToFile;
import com.conference.meeting.dto.MeetingRegisterDto;
import com.conference.meeting.dto.MeetingRequestDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.meeting.port.outbound.MeetingRepository;
import com.conference.user.port.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class MeetingService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final MeetingEntityMapper meetingEntityMapper = new MeetingEntityMapper();
    private final MeetingSaveToFile meetingSaveToFile = new MeetingSaveToFile();

    public List<MeetingResponseDto> getUserMeetings(String login) {

        List<MeetingResponseDto> meetingResponseDtos = meetingRepository.getUserMeetings(login);

        if (meetingResponseDtos.isEmpty())
            throw new CustomException("Użytkownik o podanym loginie nie bierze udział w żadnej prelekcji", 500);


        return meetingResponseDtos;
    }

    public MeetingResponseDto registerMeeting(MeetingRequestDto meetingRequestDto) {

        MeetingRegisterDto meetingRegisterDto = new MeetingRegisterDto(
                meetingRequestDto.username(),
                meetingRequestDto.email(),
                meetingEntityMapper.stringToOffsetTime(meetingRequestDto.time()),
                meetingRequestDto.topicId());

        Long userId = userRepository.findIdByUsernameAndEmail(meetingRequestDto.username(), meetingRequestDto.email())
                .orElseThrow(() -> new CustomException("Użytkownik o loginie " + meetingRegisterDto.username() +
                        " nie jest powiązany z emailem " + meetingRegisterDto.email(), 500));

        if (meetingRepository.findByUsernameAndTime(meetingRequestDto.username(), meetingRegisterDto.time()).isPresent())
            throw new CustomException("Użytkownik o loginie " + meetingRegisterDto.username() +
                    " jest już zapisany na prelekcję o godzinie " + meetingRequestDto.time(), 500);

        if (meetingRepository.countByTopicId(meetingRequestDto.topicId()) >= 5)
            throw new CustomException("Brak wolnych miejsc na tę ścieżkę tematyczną", 500);

        meetingSaveToFile.saveUserRegisterToFile(meetingRequestDto);

        return meetingRepository.registerMeeting(meetingRegisterDto, userId);
    }


}
