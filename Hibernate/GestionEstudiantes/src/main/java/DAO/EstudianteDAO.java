package DAO;

import entity.Estudiante;
import org.hibernate.Session;

public class EstudianteDAO extends BaseDAO<Estudiante> {
    public EstudianteDAO(Session session) {
        super(session, Estudiante.class);
    }
}
