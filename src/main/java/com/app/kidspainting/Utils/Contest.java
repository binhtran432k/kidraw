package com.app.kidspainting.Utils;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Contest")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contestId;
    @Column(name = "ContestName", nullable = false)
    private String contestName; 
    @Column(name = "ContestBody", nullable = false)
    private String contestBody;
    @Column(name = "MaxContestParticipant", nullable = false)
    private Integer maxContestParticipant;
    @Column(name = "ContestStatus", nullable = false)
    private String contestStatus;
    @Column(name = "StartTime", nullable = false)
    private String startTime;
    @Column(name = "endTime", nullable = false)
    private String endTime;
}
