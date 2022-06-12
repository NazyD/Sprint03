package com.oneseven.Project07.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllExceptionHandler {

    //@ResponseStatus(HttpStatus.NOT_FOUND);
    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseBody
    public String handleTeamNotFoundException(TeamNotFoundException cause) {
        return cause.getMessage();
    }

    @ExceptionHandler(StoryNotFoundException.class)
    @ResponseBody
    public String handleStoryNotFoundException(StoryNotFoundException cause) {
        return cause.getMessage();
    }

    @ExceptionHandler(SprintNotFoundException.class)
    @ResponseBody
    public String handleSprintNotFoundException(SprintNotFoundException cause) {
        return cause.getMessage();
    }
}
