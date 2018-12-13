package me.vincent.spring.tutorial.advice;

import me.vincent.spring.tutorial.viewmodel.ResultBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

/**
 * 加注释
 *
 * @description: Controller Exception控制
 * @author: Vincent Huang
 * @version:
 */

@ControllerAdvice
public class GlobalExceptionHandler
//        extends ResponseEntityExceptionHandler
{

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class, MethodArgumentNotValidException.class,})
    public ResponseEntity<ResultBean> handleAllException(WebRequest request, Exception ex) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        }

        return ResponseEntity.<ResultBean>of(Optional.of(ResultBean.ERROR("Request Failed with Error: " + ex.getMessage())));
//        return new ResponseEntity<ResultBean>(ResultBean.ERROR("Request Failed with Error: " + ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//        return handleExceptionInternal(
//                ex,
//                ResultBean.ERROR("Request Failed with Error: " + ex.getMessage()),
//                new HttpHeaders(),
//                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
