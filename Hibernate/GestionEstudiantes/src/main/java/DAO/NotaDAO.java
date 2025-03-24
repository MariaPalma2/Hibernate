package DAO;

import entity.Nota;
import org.hibernate.Session;

public class NotaDAO extends BaseDAO<Nota> {
    public NotaDAO(Session session) {
        super(session, Nota.class);
    }
}
