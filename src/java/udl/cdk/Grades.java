/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udl.cdk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Albert
 */
@Entity
@Table(name = "GRADES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grades.findAll", query = "SELECT g FROM Grades g")
    , @NamedQuery(name = "Grades.findById", query = "SELECT g FROM Grades g WHERE g.gradesPK.id = :id")
    , @NamedQuery(name = "Grades.findByStudentid", query = "SELECT g FROM Grades g WHERE g.gradesPK.studentid = :studentid")
    , @NamedQuery(name = "Grades.findByGrades", query = "SELECT g FROM Grades g WHERE g.grades = :grades")})
public class Grades implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GradesPK gradesPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GRADES")
    private Double grades;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Exams exams;
    @JoinColumn(name = "STUDENTID", referencedColumnName = "STUDENTID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;

    public Grades() {
    }

    public Grades(GradesPK gradesPK) {
        this.gradesPK = gradesPK;
    }

    public Grades(int id, int studentid) {
        this.gradesPK = new GradesPK(id, studentid);
    }

    public GradesPK getGradesPK() {
        return gradesPK;
    }

    public void setGradesPK(GradesPK gradesPK) {
        this.gradesPK = gradesPK;
    }

    public Double getGrades() {
        return grades;
    }

    public void setGrades(Double grades) {
        this.grades = grades;
    }

    public Exams getExams() {
        return exams;
    }

    public void setExams(Exams exams) {
        this.exams = exams;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gradesPK != null ? gradesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grades)) {
            return false;
        }
        Grades other = (Grades) object;
        if ((this.gradesPK == null && other.gradesPK != null) || (this.gradesPK != null && !this.gradesPK.equals(other.gradesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "udl.cdk.Grades[ gradesPK=" + gradesPK + " ]";
    }
    
}
