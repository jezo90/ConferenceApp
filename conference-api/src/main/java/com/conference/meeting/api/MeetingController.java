package com.conference.meeting.api;


import com.conference.meeting.mapper.MeetingMapper;
import com.conference.meeting.model.MeetingRequest;
import com.conference.meeting.model.MeetingResponse;
import com.conference.meeting.port.inbound.MeetingComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/meeting")
public class MeetingController {
    private final MeetingComponent meetingComponent;
    private final MeetingMapper meetingMapper = new MeetingMapper();

    @GetMapping()
    public ResponseEntity<List<String>> plan() {
        List<String> plan = Arrays.asList(
                "Data konferencji: 1 czerwca 2021",
                "Pierwsza prelekcja odbędzie się o godzinie 10:00 i kończy o 11:45",
                "Tematami prelekcji będzie: 1.C++, 2.C#, 3.Java",
                "Druga prelekcja odbędzie się o godzinie 12:00, a zakończy się o 13:45",
                "Jej tematami będzie: 4.MySQl, 5.PostgreSQl, 6.MSSQL",
                "Trzecia prelekcja zaczyna się o godzinie 14:00 i koniec przewidywane jest na 15:45",
                "Tematy na tę prelekcje to: 7.Facebook, 8.Instagram, 9.Twitter",
                "Na każdy z tematów maksymalna liczba uczestników wynosi 5.");

        return ResponseEntity.ok(plan);
    }

    @GetMapping("/{login}")
    public ResponseEntity<List<MeetingResponse>> UserMeetings(@PathVariable("login") String login)
    {
        return ResponseEntity.ok(
                meetingComponent.getUserMeetings(login)
                .stream().map(meetingMapper::map).toList());
    }

    @PostMapping("/register")
    public ResponseEntity<MeetingResponse> registerMeeting(@RequestBody MeetingRequest meetingRequest)
    {
        return ResponseEntity.ok(
                meetingMapper.map(
                        meetingComponent.registerMeeting(
                                meetingMapper.map(meetingRequest))));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeMeeting(@RequestBody MeetingRequest meetingRequest)
    {
        meetingComponent.removeMeeting(meetingMapper.map(meetingRequest));

        return ResponseEntity.ok("Poprawnie wypisano z prelekcji");
    }

}
