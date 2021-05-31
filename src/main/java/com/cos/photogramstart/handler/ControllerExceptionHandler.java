package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CmRestDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {
//        return new CmRestDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
        // 1. CMRestDto -> 클라이언트에게 응답할 때는 script가 좋음
        // 2. ajax 통신에는 CMRestDto가 좋음
        // 3. android - CMRestDto가 좋음
        return Script.back(e.getErrorMap().toString());
    }

}