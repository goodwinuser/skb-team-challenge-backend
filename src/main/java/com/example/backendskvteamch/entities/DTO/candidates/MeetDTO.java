package com.example.backendskvteamch.entities.DTO.candidates;

public record MeetDTO(Long id,
                      Long interviewer_id,
                      Long interviewee_id,
                      String datetime,
                      String caldav_url
) {
}
