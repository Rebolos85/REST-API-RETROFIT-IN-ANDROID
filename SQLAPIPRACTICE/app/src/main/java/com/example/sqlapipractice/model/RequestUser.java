package com.example.sqlapipractice.model;

import com.google.gson.annotations.SerializedName;

public class RequestUser {

    /*
      Si seraalized name kay ginagamit siya for understand the variable and json bisan unsa pa imoha i name sa imohang json
      as long as naka annotate ang variable nga sa json kay firstname na siya pasabot
    */
    @SerializedName("firstname")
    private String userFirstName;
    @SerializedName("lastname")
    private String userLastName;
    @SerializedName("age")
    private Integer userAgeNumber;


    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Integer getUserAgeNumber() {
        return userAgeNumber;
    }

    public void setUserAgeNumber(Integer userAgeNumber) {
        this.userAgeNumber = userAgeNumber;
    }
}
