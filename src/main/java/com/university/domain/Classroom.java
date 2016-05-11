package com.university.domain;

import com.university.dao.Identified;


public class  Classroom implements Identified<Integer> {
    private Integer id;
    private String number;
    private String address;

    public Classroom(){

    }

    public Classroom(String number, String address){
        this.setAddress(address);
        this.setNumber(number);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
