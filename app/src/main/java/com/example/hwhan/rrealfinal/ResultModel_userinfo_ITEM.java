package com.example.hwhan.rrealfinal;

import java.io.Serializable;

public class ResultModel_userinfo_ITEM implements Serializable {

    private String id;
    private String password;
    private String email;
    private String number;
    private String FAVOR1X;
    private String FAVOR1Y;
    private String FAVOR2X;
    private String FAVOR2Y;
    private String FAVOR3X;
    private String FAVOR3Y;

    private String CROP1;
    private String CROP2;
    private String CROP3;

    public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNumber() {
            return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFAVOR1X() {
        return FAVOR1X;
    }

    public void setFAVOR1X(String FAVOR1X) {
        this.FAVOR1X = FAVOR1X;
    }

    public String getFAVOR1Y() {
        return FAVOR1Y;
    }

    public void setFAVOR1Y(String FAVOR1Y) {
        this.FAVOR1Y = FAVOR1Y;
    }

    public String getFAVOR2X() {
        return FAVOR2X;
    }

    public void setFAVOR2X(String FAVOR2X) {
        this.FAVOR2X = FAVOR2X;
    }

    public String getFAVOR2Y() {
        return FAVOR2Y;
    }

    public void setFAVOR2Y(String FAVOR2Y) {
        this.FAVOR2Y = FAVOR2Y;
    }

    public String getFAVOR3X() {
        return FAVOR3X;
    }

    public void setFAVOR3X(String FAVOR3X) {
        this.FAVOR3X = FAVOR3X;
    }

    public String getFAVOR3Y() {
        return FAVOR3Y;
    }

    public void setFAVOR3Y(String FAVOR3Y) {
        this.FAVOR3Y = FAVOR3Y;
    }

    public String getCROP1() {
        return CROP1;
    }

    public void setCROP1(String CROP1) {
        this.CROP1 = CROP1;
    }

    public String getCROP2() {
        return CROP2;
    }

    public void setCROP2(String CROP2) {
        this.CROP2 = CROP2;
    }

    public String getCROP3() {
        return CROP3;
    }

    public void setCROP3(String CROP3) {
        this.CROP3 = CROP3;
    }
}
