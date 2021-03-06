package it.unisalento.se.dao;
// Generated 23-lug-2018 9.32.08 by Hibernate Tools 5.2.0.Final


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Exam generated by hbm2java
 */
@Entity
@Table(name="exam"
    ,catalog="university_se"
)
public class Exam  implements java.io.Serializable {


     private Integer id;
     private Classroom classroom;
     private Subject subject;
     private Timeslot timeslot;
     private String description;
     private Set<ExamResults> examResultses = new HashSet<ExamResults>(0);

    public Exam() {
    }

	
    public Exam(Classroom classroom, Subject subject, Timeslot timeslot) {
        this.classroom = classroom;
        this.subject = subject;
        this.timeslot = timeslot;
    }
    public Exam(Classroom classroom, Subject subject, Timeslot timeslot, String description, Set<ExamResults> examResultses) {
       this.classroom = classroom;
       this.subject = subject;
       this.timeslot = timeslot;
       this.description = description;
       this.examResultses = examResultses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="classroom", nullable=false)
    public Classroom getClassroom() {
        return this.classroom;
    }
    
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="subject", nullable=false)
    public Subject getSubject() {
        return this.subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="timeslot", nullable=false)
    public Timeslot getTimeslot() {
        return this.timeslot;
    }
    
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    
    @Column(name="description", length=100)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="exam")
    public Set<ExamResults> getExamResultses() {
        return this.examResultses;
    }
    
    public void setExamResultses(Set<ExamResults> examResultses) {
        this.examResultses = examResultses;
    }




}


