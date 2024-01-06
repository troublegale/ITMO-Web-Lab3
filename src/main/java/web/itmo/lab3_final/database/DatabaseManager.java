package web.itmo.lab3_final.database;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.primefaces.PrimeFaces;
import web.itmo.lab3_final.model.Point;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("databaseManager")
@SessionScoped
public class DatabaseManager implements Serializable {

    private final EntityManager entityManager = ConnectionEstablisher.getFactory().createEntityManager();

    private int count = 0;

    public int getCount() {
        return count;
    }

    @Transactional
    public void addPoint(Point point) {
        entityManager.getTransaction().begin();
        entityManager.persist(point);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void addPointWithGraph() {
        Map<String, String> coordinates = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        try {
            var x = Float.parseFloat(coordinates.get("X"));
            var y = Float.parseFloat(coordinates.get("Y"));
            var r = Integer.parseInt(coordinates.get("R"));
            Point point = new Point(x, y, r);
            addPoint(point);
            PrimeFaces.current().ajax().addCallbackParam("isResult", point.isResult());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Wrong arguments were likely passed: " + e);
        }
    }

    public List<Point> loadPoints() {
        var cm = entityManager.getCriteriaBuilder().createQuery(Point.class);
        Root<Point> root = cm.from(Point.class);
        var result = entityManager.createQuery(cm.select(root)).getResultList();
        count = result.size();
        return result;
    }

}
