package web.itmo.lab3_final.database;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.primefaces.PrimeFaces;
import web.itmo.lab3_final.model.AreaChecker;
import web.itmo.lab3_final.model.Point;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("databaseManager")
@SessionScoped
public class DatabaseManager implements Serializable {

    private final EntityManager entityManager = ConnectionEstablisher.getFactory().createEntityManager();

    // SUPER AMOGUS asdasdad

    @Transactional
    public void addPoint(Point point) {
        Point newPoint = new Point();
        newPoint.setX(point.getX());
        newPoint.setY(point.getY());
        newPoint.setR(point.getR());
        newPoint.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        entityManager.getTransaction().begin();
        entityManager.persist(newPoint);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void addPointWithGraph() {
        Map<String, String> coordinates = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        try {
            var x = Double.parseDouble(coordinates.get("X"));
            var y = Double.parseDouble(coordinates.get("Y"));
            var r = Double.parseDouble(coordinates.get("R"));
            Point point = new Point();
            point.setX(x);
            point.setY(y);
            point.setR(r);
            point.setResult(AreaChecker.checkHit(x, y, r));
            addPoint(point);
            PrimeFaces.current().ajax().addCallbackParam("isResult", point.isResult());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Wrong arguments were likely passed: " + e);
        }
    }

    public List<Point> loadPoints() {
        var cm = entityManager.getCriteriaBuilder().createQuery(Point.class);
        Root<Point> root = cm.from(Point.class);
        return entityManager.createQuery(cm.select(root)).getResultList();
    }

    public long getCount() {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Point.class)));
        cq.where();
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Transactional
    public void truncateTable() {
        entityManager.getTransaction().begin();
        String sql = "TRUNCATE TABLE the_points";
        Query query = entityManager.createNativeQuery(sql);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

}
