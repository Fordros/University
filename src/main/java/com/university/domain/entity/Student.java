package com.university.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "student")
public class Student implements Serializable  {


	private static final long serialVersionUID = 1L;

	private Integer id;
    private String firstName;
    private String lastName;
    private String contactInformation;
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

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_GROUP")//, referencedColumnName = "ID")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

   /* @Override
    public String toString(){
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                '}' + '\n';
    }*/
}
