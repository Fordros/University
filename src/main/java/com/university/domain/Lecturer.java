package com.university.domain;

import com.university.dao.Identified;


public class Lecturer extends Person implements Identified<Integer> {

    String qualification;

    public Lecturer(){

    }
    public Lecturer(Integer id, String firstName, String lastName, String contactInformation, String qualification){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setContactInformation(contactInformation);
        this.setQualification(qualification);
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
