package org.talend.esbconsole.server.start.exception;

import org.talend.esbconsole.server.tools.base.exception.DataOperationException;
import org.talend.esbconsole.server.tools.base.exception.HTTPRequestFailedException;
import org.talend.esbconsole.server.tools.base.exception.ServerInfoReadException;
import org.talend.esbconsole.server.tools.base.exception.ServiceConflictException;
import org.talend.esbconsole.server.tools.base.exception.authentication.AuthenticationException;
import org.talend.esbconsole.server.tools.base.exception.authentication.LoginException;
import org.talend.esbconsole.server.tools.base.exception.authentication.PasswordErrorException;
import org.talend.esbconsole.server.tools.base.exception.file.FileException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXException;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局自定义统一异常处理
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({JMXException.class})
    public ResponseEntity<ResponseResult<String>> handleJMXException(JMXException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }


    @ExceptionHandler({FileException.class})
    public ResponseEntity<ResponseResult<String>> handleFileException(FileException e) {
        // e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ResponseResult<String>> handleAuthenticationException(AuthenticationException e) {
        // e.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }

    @ExceptionHandler({LoginException.class})
    public ResponseEntity<ResponseResult<String>> handleLoginException(LoginException e) {
        // e.printStackTrace();
        return ResponseEntity.status(515).body(new ResponseResult<>(515, e.getMessage()));
    }

    @ExceptionHandler({HTTPRequestFailedException.class})
    public ResponseEntity<ResponseResult<String>> handleHTTPRequestFailedException(HTTPRequestFailedException e) {
        // e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseResult<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> result = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("参数校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ResponseResult<String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> result = new HashMap<>();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

    @ExceptionHandler({ServerInfoReadException.class})
    public ResponseEntity<ResponseResult<String>> handleServerInfoReadException(ServerInfoReadException e) {
        // e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler({PasswordErrorException.class})
    public ResponseEntity<ResponseResult<String>> handlePasswordErrorException(PasswordErrorException e) {
        // e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler({ServiceConflictException.class})
    public ResponseEntity<ResponseResult<String>> handleServiceConflictException(ServiceConflictException e) {
        // e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "与服务【" + e.getMessage() + "】bundle冲突"));
    }

    @ExceptionHandler({DataOperationException.class})
    public ResponseEntity<ResponseResult<String>> handleDataOperationException(DataOperationException e) {
        // e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }
}
