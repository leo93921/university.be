package it.unisalento.se.saw.dao;
// Generated 9-mag-2018 14.34.54 by Hibernate Tools 5.2.0.Final


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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ChatGroup generated by hbm2java
 */
@Entity
@Table(name="chat_group"
    ,catalog="university_se"
)
public class ChatGroup  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set<User> users = new HashSet<User>(0);
     private Set<Message> messages = new HashSet<Message>(0);

    public ChatGroup() {
    }

	
    public ChatGroup(String name) {
        this.name = name;
    }
    public ChatGroup(String name, Set<User> users, Set<Message> messages) {
       this.name = name;
       this.users = users;
       this.messages = messages;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="name", nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="chat_group_has_user", catalog="university_se", joinColumns = { 
        @JoinColumn(name="chat_group", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="user", nullable=false, updatable=false) })
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="chatGroup")
    public Set<Message> getMessages() {
        return this.messages;
    }
    
    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }




}


