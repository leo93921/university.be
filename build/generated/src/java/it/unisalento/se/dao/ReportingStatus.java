package it.unisalento.se.dao;
// Generated 2-lug-2018 15.46.49 by Hibernate Tools 5.2.0.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ReportingStatus generated by hbm2java
 */
@Entity
@Table(name="reporting_status"
    ,catalog="university_se"
)
public class ReportingStatus  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set<Reporting> reportings = new HashSet<Reporting>(0);

    public ReportingStatus() {
    }

	
    public ReportingStatus(String name) {
        this.name = name;
    }
    public ReportingStatus(String name, Set<Reporting> reportings) {
       this.name = name;
       this.reportings = reportings;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="reportingStatus")
    public Set<Reporting> getReportings() {
        return this.reportings;
    }
    
    public void setReportings(Set<Reporting> reportings) {
        this.reportings = reportings;
    }




}


