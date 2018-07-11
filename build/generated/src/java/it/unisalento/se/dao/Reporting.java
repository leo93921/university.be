package it.unisalento.se.dao;
// Generated 11-lug-2018 9.15.59 by Hibernate Tools 5.2.0.Final


import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Reporting generated by hbm2java
 */
@Entity
@Table(name="reporting"
    ,catalog="university_se"
)
public class Reporting  implements java.io.Serializable {


     private Integer id;
     private Classroom classroom;
     private ReportingStatus reportingStatus;
     private SupportDevice supportDevice;
     private User user;
     private String note;
     private Date lastModified;
    private String problemDescription;

    public Reporting() {
    }


    public Reporting(Classroom classroom, ReportingStatus reportingStatus, User user, Date lastModified, String problemDescription) {
        this.classroom = classroom;
        this.reportingStatus = reportingStatus;
        this.user = user;
        this.lastModified = lastModified;
        this.problemDescription = problemDescription;
    }

    public Reporting(Classroom classroom, ReportingStatus reportingStatus, SupportDevice supportDevice, User user, String note, Date lastModified, String problemDescription) {
       this.classroom = classroom;
       this.reportingStatus = reportingStatus;
       this.supportDevice = supportDevice;
       this.user = user;
       this.note = note;
       this.lastModified = lastModified;
        this.problemDescription = problemDescription;
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
    @JoinColumn(name="status", nullable=false)
    public ReportingStatus getReportingStatus() {
        return this.reportingStatus;
    }
    
    public void setReportingStatus(ReportingStatus reportingStatus) {
        this.reportingStatus = reportingStatus;
    }

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name = "item")
    public SupportDevice getSupportDevice() {
        return this.supportDevice;
    }
    
    public void setSupportDevice(SupportDevice supportDevice) {
        this.supportDevice = supportDevice;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="done_by", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="note", length=150)
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_modified", nullable=false, length=19)
    public Date getLastModified() {
        return this.lastModified;
    }
    
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }


    @Column(name = "problem_description", nullable = false)
    public String getProblemDescription() {
        return this.problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }




}


