package com.university.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "classroom")
public class  Classroom implements Serializable  {
	

	private static final long serialVersionUID = 1L;
    private Integer id;
    private String number;
    private String address;

    public Classroom(){

    }

    public Classroom(String number, String address){
        this.setAddress(address);
        this.setNumber(number);
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

    @Column(name = "NUMBER", unique = true, nullable = false, length = 10)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "ADDRESS", unique = true, nullable = false, length = 10)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
