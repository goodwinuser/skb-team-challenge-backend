package com.example.backendskvteamch.entities.DTO.Candidates;

public record MeetDTO(Long id,
                      Long interviewer_id,
                      Long interviewee_id,
                      String datetime,
                      String caldav_url
) {
}
