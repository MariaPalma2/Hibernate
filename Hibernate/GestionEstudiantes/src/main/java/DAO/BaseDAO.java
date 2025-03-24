package DAO;

import org.hibernate.Session;
import java.util.List;

public class BaseDAO<T> {
    private Session session;
    private Class<T> entityType;

    public BaseDAO(Session session, Class<T> entityType) {
        this.session = session;
        this.entityType = entityType;
    }

    public void save(T entity) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    public T findById(Long id) {
        return session.get(entityType, id);
    }

    public void update(T entity) {
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    public void delete(Long id) {
        T entity = findById(id);
        if (entity != null) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        }
    }
}
