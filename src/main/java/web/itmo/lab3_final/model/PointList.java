package web.itmo.lab3_final.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import web.itmo.lab3_final.database.DatabaseManager;

import java.util.List;
import java.util.Map;

@Named("pointList")
@SessionScoped
public class PointList extends LazyDataModel<Point> {

    @Inject
    private DatabaseManager manager;


    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return manager.getCount();
    }

    @Override
    public List<Point> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        return manager.loadPoints();
    }
}
