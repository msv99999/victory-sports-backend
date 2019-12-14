package org.victoryfoundation.sportsapp.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author mbharti
 * @date 14/12/19
 */
public class ResponseBuilder {

    public static ResponseEntity<Object> buildResponse(Object obj, HttpStatus status) {
        return new ResponseEntity<>(obj, status);
    }

    public static ResponseEntity<Object> buildErrorResponse(String msg, HttpStatus status) {
        return buildErrorResponse(msg, null, status);
    }

    public static ResponseEntity<Object> buildErrorResponse(String msg, Throwable t, HttpStatus status) {
        Map<String, String> message = new HashMap<>();
        message.put("error", msg);
        if(t!=null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            message.put("trace", sw.toString());
        }
        return new ResponseEntity<>(message, status);
    }


}
