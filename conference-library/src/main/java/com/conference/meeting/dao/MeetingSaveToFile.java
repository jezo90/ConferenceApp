package com.conference.meeting.dao;

import com.conference.exception.CustomException;
import com.conference.meeting.dto.MeetingRequestDto;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MeetingSaveToFile {

    public void saveUserRegisterToFile(MeetingRequestDto meetingRequestDto) {
        try {
            String register = "Zapisano użytkownika " + meetingRequestDto.username()
                    + " na godzinę " + meetingRequestDto.time()
                    + " na ścieżkę tematyczną o id " + meetingRequestDto.topicId() + "\n";
            Files.writeString(Paths.get("D:/register.txt"), register, StandardOpenOption.APPEND);
        } catch (Exception exception) {
            throw new CustomException("Błąd podczas zapisywania do pliku", 500);
        }
    }
}
