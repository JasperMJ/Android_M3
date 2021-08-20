/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package M3app;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "MEMOIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memoir.findAll", query = "SELECT m FROM Memoir m")
    , @NamedQuery(name = "Memoir.findByMemoirid", query = "SELECT m FROM Memoir m WHERE m.memoirid = :memoirid")
    , @NamedQuery(name = "Memoir.findByMovieName", query = "SELECT m FROM Memoir m WHERE m.movieName = :movieName")
    , @NamedQuery(name = "Memoir.findByMovieReleaseDate", query = "SELECT m FROM Memoir m WHERE m.movieReleaseDate = :movieReleaseDate")
    , @NamedQuery(name = "Memoir.findByWatchDate", query = "SELECT m FROM Memoir m WHERE m.watchDate = :watchDate")
    , @NamedQuery(name = "Memoir.findByWatchTime", query = "SELECT m FROM Memoir m WHERE m.watchTime = :watchTime")
    , @NamedQuery(name = "Memoir.findByComment", query = "SELECT m FROM Memoir m WHERE m.comment = :comment")
    , @NamedQuery(name = "Memoir.findByRating", query = "SELECT m FROM Memoir m WHERE m.rating = :rating")
    //task 3(d)
    , @NamedQuery(name = "Memoir.findByRatingANDlocation", query = "SELECT m FROM Memoir m WHERE m.cinemaid.location = :location AND m.rating = :rating")
})
public class Memoir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "MEMOIRID")
    private String memoirid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVIE_RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date movieReleaseDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WATCH_DATE")
    @Temporal(TemporalType.DATE)
    private Date watchDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WATCH_TIME")
    @Temporal(TemporalType.TIME)
    private Date watchTime;
    @Size(max = 500)
    @Column(name = "COMMENT")
    private String comment;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "RATING")
    private BigDecimal rating;
    @JoinColumn(name = "CINEMAID", referencedColumnName = "CINEMAID")
    @ManyToOne(optional = false)
    private Cinema cinemaid;
    @JoinColumn(name = "PERSONID", referencedColumnName = "PERSONID")
    @ManyToOne(optional = false)
    private Person personid;

    public Memoir() {
    }

    public Memoir(String memoirid) {
        this.memoirid = memoirid;
    }

    public Memoir(String memoirid, String movieName, Date movieReleaseDate, Date watchDate, Date watchTime, BigDecimal rating) {
        this.memoirid = memoirid;
        this.movieName = movieName;
        this.movieReleaseDate = movieReleaseDate;
        this.watchDate = watchDate;
        this.watchTime = watchTime;
        this.rating = rating;
    }

    public String getMemoirid() {
        return memoirid;
    }

    public void setMemoirid(String memoirid) {
        this.memoirid = memoirid;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(Date movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public Date getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
    }

    public Date getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(Date watchTime) {
        this.watchTime = watchTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Cinema getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(Cinema cinemaid) {
        this.cinemaid = cinemaid;
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
        hash += (memoirid != null ? memoirid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memoir)) {
            return false;
        }
        Memoir other = (Memoir) object;
        if ((this.memoirid == null && other.memoirid != null) || (this.memoirid != null && !this.memoirid.equals(other.memoirid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "M3app.Memoir[ memoirid=" + memoirid + " ]";
    }
    
}
