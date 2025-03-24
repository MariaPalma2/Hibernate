package DAO;

import entity.Curso;
import org.hibernate.Session;

public class CursoDAO extends BaseDAO<Curso> {
    public CursoDAO(Session session) {
        super(session, Curso.class);
    }
}
