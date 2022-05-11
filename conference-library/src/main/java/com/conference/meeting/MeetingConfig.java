package com.conference.meeting;

import com.conference.meeting.port.outbound.MeetingRepository;
import com.conference.user.port.outbound.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MeetingConfig {
    @Bean
    MeetingFacade meetingFacade(MeetingRepository meetingRepository, UserRepository userRepository) {
        MeetingService meetingService = new MeetingService(meetingRepository, userRepository);
        return new MeetingFacade(meetingService);
    }
}
