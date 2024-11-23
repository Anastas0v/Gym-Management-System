package com.gymbuddy.model.shared;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "working_info")
public class WorkingInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "working_hours_id")
    private Long id;

    @Column(name = "working_day", nullable = false)
    private String day;

    @Column(name = "opening_time", nullable = false)
    private String openingTime;

    @Column(name = "closing_time")
    private String closingTime;
}