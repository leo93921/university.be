package it.unisalento.se.dao;
// Generated 11-lug-2018 12.52.10 by Hibernate Tools 5.2.0.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LessonEvaluation generated by hbm2java
 */
@Entity
@Table(name="lesson_evaluation"
    ,catalog="university_se"
)
public class LessonEvaluation  implements java.io.Serializable {


     private Integer id;
     private Lesson lesson;
     private User user;
     private int score;
     private String note;

    public LessonEvaluation() {
    }

	
    public LessonEvaluation(Lesson lesson, User user, int score) {
        this.lesson = lesson;
        this.user = user;
        this.score = score;
    }
    public LessonEvaluation(Lesson lesson, User user, int score, String note) {
       this.lesson = lesson;
       this.user = user;
       this.score = score;
       this.note = note;
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
    @JoinColumn(name="recipient", nullable=false)
    public Lesson getLesson() {
        return this.lesson;
    }
    
    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sender", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="score", nullable=false)
    public int getScore() {
        return this.score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    
    @Column(name="note", length=150)
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }




}


