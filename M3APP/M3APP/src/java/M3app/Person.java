/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3app;

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
 * @author Jasper
 */
@Entity
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findByPersonid", query = "SELECT p FROM Person p WHERE p.personid = :personid")
    , @NamedQuery(name = "Person.findByFirstname", query = "SELECT p FROM Person p WHERE p.firstname = :firstname")
    , @NamedQuery(name = "Person.findBySurname", query = "SELECT p FROM Person p WHERE p.surname = :surname")
    , @NamedQuery(name = "Person.findByGender", query = "SELECT p FROM Person p WHERE p.gender = :gender")
    , @NamedQuery(name = "Person.findByDob", query = "SELECT p FROM Person p WHERE p.dob = :dob")
    , @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address")
    , @NamedQuery(name = "Person.findByState", query = "SELECT p FROM Person p WHERE p.state = :state")
    , @NamedQuery(name = "Person.findByPostcode", query = "SELECT p FROM Person p WHERE p.postcode = :postcode")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PERSONID")
    private String personid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SURNAME")
    private String surname;
    @Size(max = 10)
    @Column(name = "GENDER")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATE")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "POSTCODE")
    private String postcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personid")
    private Collection<Memoir> memoirCollection;
    @OneToMany(mappedBy = "personid")
    private Collection<Credential> credentialCollection;

    public Person() {
    }

    public Person(String personid) {
        this.personid = personid;
    }

    public Person(String personid, String firstname, String surname, Date dob, String address, String state, String postcode) {
        this.personid = personid;
        this.firstname = firstname;
        this.surname = surname;
        this.dob = dob;
        this.address = address;
        this.state = state;
        this.postcode = postcode;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @XmlTransient
    public Collection<Memoir> getMemoirCollection() {
        return memoirCollection;
    }

    public void setMemoirCollection(Collection<Memoir> memoirCollection) {
        this.memoirCollection = memoirCollection;
    }

    @XmlTransient
    public Collection<Credential> getCredentialCollection() {
        return credentialCollection;
    }

    public void setCredentialCollection(Collection<Credential> credentialCollection) {
        this.credentialCollection = credentialCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personid != null ? personid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personid == null && other.personid != null) || (this.personid != null && !this.personid.equals(other.personid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "M3app.Person[ personid=" + personid + " ]";
    }
    
}
