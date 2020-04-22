package com.limonnana.backend02.entity;

public class RestResponse {


    public static final String SUCCESS = "SUCCESS";
    public static final String SUCCESS_STATUS = "200";

    private String status = SUCCESS_STATUS;
    private String response  = SUCCESS;
    private String error;

    public String getStatus() {
       // if(this.status == null || this.status == ""){
       //     this.status = SUCCESS_STATUS;
       // }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
     //   if(this.response == null || this.response == "") {
     //       this.response = SUCCESS;
     //   }
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
