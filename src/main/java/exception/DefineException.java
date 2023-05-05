package exception;

import lombok.Data;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Data
public class DefineException extends RuntimeException {
    private Integer status;
    private String message;

    public DefineException(Integer status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

}
