package com.conference.meeting.dao;

import com.conference.exception.CustomException;
import com.conference.meeting.dto.MeetingDto;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;

public class MeetingSaveToFile {

    public void meetingDtoToFile(MeetingDto meetingDto) {
        try {
            String register = "Zapisano użytkownika " + meetingDto.username()
                    + " na godzinę " + meetingDto.time().format(DateTimeFormatter.ofPattern("HH:mm"))
                    + " na ścieżkę tematyczną o id " + meetingDto.topicId() + "\n";
            Files.writeString(Paths.get("D:/register.txt"), register, StandardOpenOption.APPEND);
        } catch (Exception exception) {
            throw new CustomException("Błąd podczas zapisywania do pliku", 500);
        }
    }
}
