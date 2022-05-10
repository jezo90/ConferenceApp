package com.conference.user;

import com.conference.user.port.outbound.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfig {

    @Bean
    UserFacade userFacade(UserRepository userRepository) {
        UserService userService = new UserService(userRepository);
        return new UserFacade(userService);
    }
}
