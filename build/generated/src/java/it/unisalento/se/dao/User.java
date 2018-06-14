package it.unisalento.se.dao;
// Generated 14-giu-2018 10.05.29 by Hibernate Tools 5.2.0.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
    ,catalog="university_se"
)
public class User  implements java.io.Serializable {


     private Integer id;
     private CourseOfStudy courseOfStudy;
     private UserType userType;
     private String name;
     private String surname;
     private String email;
     private Set<Reporting> reportings = new HashSet<Reporting>(0);
     private Set<ChatGroup> chatGroups = new HashSet<ChatGroup>(0);
     private Set<Evaluation> evaluations = new HashSet<Evaluation>(0);
     private Set<Message> messages = new HashSet<Message>(0);
     private Set<Subject> subjects = new HashSet<Subject>(0);
     private Set<ExamResults> examResultses = new HashSet<ExamResults>(0);

    public User() {
    }

	
    public User(UserType userType, String name, String surname, String email) {
        this.userType = userType;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public User(CourseOfStudy courseOfStudy, UserType userType, String name, String surname, String email, Set<Reporting> reportings, Set<ChatGroup> chatGroups, Set<Evaluation> evaluations, Set<Message> messages, Set<Subject> subjects, Set<ExamResults> examResultses) {
       this.courseOfStudy = courseOfStudy;
       this.userType = userType;
       this.name = name;
       this.surname = surname;
       this.email = email;
       this.reportings = reportings;
       this.chatGroups = chatGroups;
       this.evaluations = evaluations;
       this.messages = messages;
       this.subjects = subjects;
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
    @JoinColumn(name="course_of_study")
    public CourseOfStudy getCourseOfStudy() {
        return this.courseOfStudy;
    }
    
    public void setCourseOfStudy(CourseOfStudy courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_type", nullable=false)
    public UserType getUserType() {
        return this.userType;
    }
    
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    
    @Column(name="name", nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="surname", nullable=false, length=45)
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    
    @Column(name="email", nullable=false, length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Reporting> getReportings() {
        return this.reportings;
    }
    
    public void setReportings(Set<Reporting> reportings) {
        this.reportings = reportings;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="chat_group_has_user", catalog="university_se", joinColumns = { 
        @JoinColumn(name="user", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="chat_group", nullable=false, updatable=false) })
    public Set<ChatGroup> getChatGroups() {
        return this.chatGroups;
    }
    
    public void setChatGroups(Set<ChatGroup> chatGroups) {
        this.chatGroups = chatGroups;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Evaluation> getEvaluations() {
        return this.evaluations;
    }
    
    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Message> getMessages() {
        return this.messages;
    }
    
    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Subject> getSubjects() {
        return this.subjects;
    }
    
    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<ExamResults> getExamResultses() {
        return this.examResultses;
    }
    
    public void setExamResultses(Set<ExamResults> examResultses) {
        this.examResultses = examResultses;
    }




}

