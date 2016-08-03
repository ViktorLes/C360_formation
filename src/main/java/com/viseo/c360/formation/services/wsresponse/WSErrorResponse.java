package com.viseo.c360.formation.services.wsresponse;

public class WSErrorResponse extends WSResponse{

    public class Error{
        String errorPurpose;

        public Error() {
        }

        public Error(String errorMessage) {
            this.errorPurpose = errorMessage;
        }

        public String getErrorPurpose() {
            return errorPurpose;
        }

        public void setErrorPurpose(String errorPurpose) {
            this.errorPurpose = errorPurpose;
        }
    }

    Error error;

    public WSErrorResponse() {
    }

    public WSErrorResponse(String errorMessage) {
        this.error = new Error(errorMessage);
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
