/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udl.cdk;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Albert
 */
@Entity
@Table(name = "EXAMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exams.findAll", query = "SELECT e FROM Exams e")
    , @NamedQuery(name = "Exams.findById", query = "SELECT e FROM Exams e WHERE e.id = :id")
    , @NamedQuery(name = "Exams.findByDescription", query = "SELECT e FROM Exams e WHERE e.description = :description")
    , @NamedQuery(name = "Exams.findByDate", query = "SELECT e FROM Exams e WHERE e.date = :date")
    , @NamedQuery(name = "Exams.findByTime", query = "SELECT e FROM Exams e WHERE e.time = :time")
    , @NamedQuery(name = "Exams.findByLocation", query = "SELECT e FROM Exams e WHERE e.location = :location")})
public class Exams implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIME")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LOCATION")
    private String location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exams")
    private Collection<Grades> gradesCollection;

    public Exams() {
    }

    public Exams(Integer id) {
        this.id = id;
    }

    public Exams(Integer id, Date date, Date time, String location) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<Grades> getGradesCollection() {
        return gradesCollection;
    }

    public void setGradesCollection(Collection<Grades> gradesCollection) {
        this.gradesCollection = gradesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exams)) {
            return false;
        }
        Exams other = (Exams) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "udl.cdk.Exams[ id=" + id + " ]";
    }
    
}
