/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udl.cdk;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Albert
 */
@Embeddable
public class GradesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STUDENTID")
    private int studentid;

    public GradesPK() {
    }

    public GradesPK(int id, int studentid) {
        this.id = id;
        this.studentid = studentid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) studentid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GradesPK)) {
            return false;
        }
        GradesPK other = (GradesPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.studentid != other.studentid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "udl.cdk.GradesPK[ id=" + id + ", studentid=" + studentid + " ]";
    }
    
}
