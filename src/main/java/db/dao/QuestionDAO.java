package db.dao;

import db.HibernateFactory;
import entity.Question;
import org.hibernate.Session;

public class QuestionDAO {

    public Question read(Long id) {
        Session session = new HibernateFactory().getSessionFactory().openSession();
        try {
            Question question = session.get(Question.class, id);
            return question;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
