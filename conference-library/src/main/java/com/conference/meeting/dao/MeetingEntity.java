package com.conference.meeting.dao;


import com.conference.user.dao.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetTime;

@Data
@Entity
@Table(name = "meetings")
public class MeetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private OffsetTime time;

    private Long topicId;

}
