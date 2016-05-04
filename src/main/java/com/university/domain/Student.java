package com.university.domain;

import com.university.dao.Identified;

/**
 * Created by Fordros on 02.04.2016.
 */
public class Student extends Person implements Identified<Integer> {

    private Group group;

    public Student(){

    }

    public Student(Integer id, String firstName, String lastName, String contactInformation,Group group){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setContactInformation(contactInformation);
        this.setGroup(group);

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString(){
        return "Student{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", group='" + getGroup() + '\'' +
                '}' + '\n';
    }
}
