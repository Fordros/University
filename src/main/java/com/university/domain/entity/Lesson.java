package com.university.domain.entity;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson  implements Serializable  {


	private static final long serialVersionUID = 1L;
    private Integer id;
    private Group group;
    private Lecturer lecturer;
    private Classroom classroom;
    private Date lessonTime;
    private String studiesTypes;

    public Lesson(){

    }



	public Lesson(Group group, Lecturer lecturer, Classroom classroom, Date lessonTime, String studiesTypes){
        this.setGroup(group);
        this.setLecturer(lecturer);
        this.setClassroom(classroom);
        this.setLessonTime(lessonTime);
        this.setStudiesTypes(studiesTypes);
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

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_GROUP", nullable = false)
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_LECTURER", nullable = false)
    public Lecturer getLecturer() {
		return lecturer;
	}



	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}


    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CLASSROOM", nullable = false)
	public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LESSONTIME", unique = true, nullable = false)
    public Date getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(Date lessonTime) {
		this.lessonTime = lessonTime;
	}

	@Column(name = "STUDIESTYPES", unique = true, nullable = false, length = 17)
	public String getStudiesTypes() {
		return studiesTypes;
	}

	public void setStudiesTypes(String studiesTypes) {
		this.studiesTypes = studiesTypes;
	}

   /* @Override
    public String toString(){
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return "Lesson{" +
                "group='" + group.getGroupNumber() + '\'' +
                ", lecturer='" + lecturer.getFirstName() + '\'' +
                ", classroom='" + classroom.getNumber() + '\'' +
                ", lessonTime='" + format.format(lessonTime) + '\'' +
                '}' + '\n';
    }

*/
}
