/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3app;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jasper
 */
@Entity
@Table(name = "CREDENTIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credential.findAll", query = "SELECT c FROM Credential c")
    , @NamedQuery(name = "Credential.findByUsername", query = "SELECT c FROM Credential c WHERE c.username = :username")
    , @NamedQuery(name = "Credential.findByPassword", query = "SELECT c FROM Credential c WHERE c.password = :password")
    , @NamedQuery(name = "Credential.findBySignUpDate", query = "SELECT c FROM Credential c WHERE c.signUpDate = :signUpDate")
    , @NamedQuery(name = "Credential.findByPersonid", query = "SELECT c FROM Credential c WHERE c.personid.personid = :personid")
})
public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIGN_UP_DATE")
    @Temporal(TemporalType.DATE)
    private Date signUpDate;
    @JoinColumn(name = "PERSONID", referencedColumnName = "PERSONID")
    @ManyToOne
    private Person personid;

    public Credential() {
    }

    public Credential(String username) {
        this.username = username;
    }

    public Credential(String username, String password, Date signUpDate) {
        this.username = username;
        this.password = password;
        this.signUpDate = signUpDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Person getPersonid() {
        return personid;
    }

    public void setPersonid(Person personid) {
        this.personid = personid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credential)) {
            return false;
        }
        Credential other = (Credential) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "M3app.Credential[ username=" + username + " ]";
    }
    
}
