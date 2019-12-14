package org.victoryfoundation.sportsapp.exception;

/**
 * @author mbharti
 * @date 14/12/19
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
