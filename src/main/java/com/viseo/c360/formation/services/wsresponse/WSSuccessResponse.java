package com.viseo.c360.formation.services.wsresponse;

import com.viseo.c360.formation.dto.BaseDTO;

public class WSSuccessResponse extends WSResponse{

    public class Success{
        BaseDTO dto;

        public Success(){}

        public Success(BaseDTO dto){
            this.dto = dto;
        }

        public BaseDTO getDto() {
            return dto;
        }

        public void setDto(BaseDTO dto) {
            this.dto = dto;
        }
    }

    Success success;

    public WSSuccessResponse(){}

    public WSSuccessResponse(BaseDTO dto){
        this.success = new Success(dto);
    }

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }
}
