package com.university.domain;

import com.university.dao.Identified;


public class Lecturer extends Person implements Identified<Integer> {

    String qualification;

    public Lecturer(){

    }
    public Lecturer(Integer id, String firstName, String lastName, String contactInformation, LecturerQualification qualification){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setContactInformation(contactInformation);
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
