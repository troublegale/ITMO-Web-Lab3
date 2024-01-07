package web.itmo.lab3_final.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Locale;

@Named("point")
@SessionScoped
@Entity
@Table(name = "the_points", schema = "s367293")
public class Point implements Serializable {

    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "sequence-generator"
//    )
//    @SequenceGenerator(
//            name = "sequence-generator",
//            sequenceName = "the_points_id_seq",
//            allocationSize = 1
//    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Point() {
        if (Double.compare(r, 0) == 0) r = 1;
    }

    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;
    @Column(name = "r")
    private double r;
    @Column(name = "result")
    private boolean result;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}