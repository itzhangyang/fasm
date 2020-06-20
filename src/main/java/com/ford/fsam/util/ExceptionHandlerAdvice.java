package com.ford.fsam.util;


import com.ford.fsam.common.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler(AlreadySignedInException.class)
    ResponseEntity<String> handleAlreadySignedInException(AlreadySignedInException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Already signed in");
    }

    @ExceptionHandler(MultiplePointAccountException.class)
    ResponseEntity<String> handleMultiplePointAccountException(MultiplePointAccountException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User has multiple point accounts");
    }

    @ExceptionHandler(NoCurrentSignInTaskException.class)
    ResponseEntity<String> handleNoCurrentSignInTaskException(MultiplePointAccountException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok().body("No sign task currently");
    }


    @ExceptionHandler(PermissionDeniedException.class)
    ResponseEntity<String> handlePermissionDeniedException(PermissionDeniedException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission Denied");
    }

    @ExceptionHandler(PointAccountAlreadyExistException.class)
    ResponseEntity<String> handlePointAccountAlreadyExistException(PointAccountAlreadyExistException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Point Account Already exist");
    }
    @ExceptionHandler(MultipleUserException.class)
    ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok().body("User already exist ");
    }
    @ExceptionHandler(MultipleUserException.class)
    ResponseEntity<String> handleMultipleUserException(MultiplePointAccountException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Multiple user with the same phone found");
    }

    @ExceptionHandler(UserAlreadyHasRoeException.class)
    ResponseEntity<String> handleUserAlreadyHasRoeException(UserAlreadyHasRoeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok().body("User already has the role");
    }


    @ExceptionHandler(UserInFoEmptyException.class)
    ResponseEntity<String> handleUserInFoEmptyException(UserAlreadyHasRoeException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok().body("User info is empty");
    }

    @ExceptionHandler(UserInfoFailedObtainException.class)
    ResponseEntity<String> handleUserInfoFailedObtainException(UserInfoFailedObtainException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok().body("Failed to obtain user info");
    }


    @ExceptionHandler(UserNotExistException.class)
    ResponseEntity<String> handleUserNotExistException(UserNotExistException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok().body("User not exist");
    }

}
