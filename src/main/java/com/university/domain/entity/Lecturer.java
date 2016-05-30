package com.university.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "lecturer")
public class Lecturer implements Serializable  {
	

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String firstName;
    private String lastName;
    private String contactInformation;
    private String qualification;

    public Lecturer(){

    }
    public Lecturer(Integer id, String firstName, String lastName, String contactInformation, String qualification){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setContactInformation(contactInformation);
        this.setQualification(qualification);
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, insertable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "CONTACT_INFORMATION", unique = false, nullable = false, length = 255)
    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
    @Column(name = "QUALIFICATION", unique = false, nullable = false, length = 50)
    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
