package com.example.personalgoals.utils.mapper;

import com.example.personalgoals.dto.GoalReadDto;
import com.example.personalgoals.model.GoalModel;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class GoalMapper {
    public GoalReadDto toDto(GoalModel model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String formattedStartDate = model.getStartDate().format(formatter);
        String formattedEndDate = model.getEndDate().format(formatter);


        return GoalReadDto.builder()
                .goal(model.getGoal())
                .goal(model.getGoalName())
                .goal(formattedStartDate)
                .goal(formattedEndDate)
                .build();
    }
}
