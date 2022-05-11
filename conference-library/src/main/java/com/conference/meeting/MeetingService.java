package com.conference.meeting;

import com.conference.exception.CustomException;
import com.conference.meeting.dao.MeetingEntityMapper;
import com.conference.meeting.dto.MeetingRegisterDto;
import com.conference.meeting.dto.MeetingRequestDto;
import com.conference.meeting.dto.MeetingResponseDto;
import com.conference.meeting.port.outbound.MeetingRepository;
import com.conference.user.port.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
class MeetingService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final MeetingEntityMapper meetingEntityMapper = new MeetingEntityMapper();

    public List<MeetingResponseDto> getUserMeetings(String login) {

        List<MeetingResponseDto> meetingResponseDtos = meetingRepository.getUserMeetings(login);

        if(meetingResponseDtos.isEmpty())
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
                .orElseThrow(() -> new CustomException("Nie istnieje użytkownik przypisany do podanego adresu email", 500));

        if (meetingRepository.findByUsernameAndTime(meetingRequestDto.username(), meetingRegisterDto.time()).isEmpty())
            throw new CustomException("Użytkownik o podanym loginie nie istnieje", 500);

        if (meetingRepository.countByTopicId(meetingRequestDto.topicId()) >= 5)
            throw new CustomException("Brak wolnych miejsc", 500);

        try {
            PrintWriter file = new PrintWriter("resources/register.txt");
            file.println("Data: " + OffsetDateTime.now());
            file.println("Do: " + meetingRequestDto.username());
            file.println("Zapisano na ścieżkę o id: " + meetingRequestDto.topicId() + "\n");
            file.close();
        }
        catch (Exception exception)
        {
            throw new CustomException("Błąd podczas zapisywania do pliku", 500);
        }


        return meetingRepository.registerMeeting(meetingRegisterDto, userId);
    }


}
