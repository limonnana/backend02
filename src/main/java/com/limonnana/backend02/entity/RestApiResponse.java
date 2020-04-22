package com.limonnana.backend02.entity;

import com.google.gson.Gson;


public class RestApiResponse {

    private String status;
    private String response;
    private Object o;
    private String objectDataJason;
    private String error;
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String SUCCESS_STATUS = "200";
    public static final String FAILED_STATUS = "400";

    public static void main(String[] args) {

        RestApiResponse r = new RestApiResponse();
        Login l = new Login();
        l.setEmail("rosenzvaig@gmail.com");
          l.setPassword("avocado1");

        System.out.println(r.response(l, Login.class));
    }
     public RestApiResponse(){ }


     public RestApiResponse(String response, String status, Object o, Class c){
       this.response = response;
       this.status = status;
       this.o = o;
     }

    public String response(Object o, Class c){
        Gson gson = new Gson();
      //  Login l = new Login();
       // l.setEmail("rosenzvaig@gmail.com");
     //   l.setPassword("avocado1");
        return gson.toJson(o, c);
    }

    public String toJson(){
        RestResponse restResponse = new RestResponse();
        restResponse.setResponse(getResponse());
        restResponse.setStatus(getStatus());
        if(getError() != null){
            restResponse.setError(getError());
        }

        if(this.response == null || this.response == "") {
            this.response = SUCCESS;
        }
        if(this.status == null || this.status == ""){
            this.status = SUCCESS_STATUS;
        }

        Gson gson = new Gson();
        return gson.toJson(restResponse);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getObjectDataJason() {
        return objectDataJason;
    }

    public void setObjectDataJason(String objectDataJason) {
        this.objectDataJason = objectDataJason;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
