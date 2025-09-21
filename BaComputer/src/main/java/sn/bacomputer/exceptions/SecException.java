package sn.bacomputer.exceptions;

import org.springframework.http.HttpStatus;


public class SecException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    // ✅ Constructeur simple avec message seulement
    public SecException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST; // statut par défaut
        this.message = message;
    }

    // ✅ Constructeur avec message + statut
    public SecException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    // ✅ Optionnel : constructeur avec message + cause + statut
    public SecException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

