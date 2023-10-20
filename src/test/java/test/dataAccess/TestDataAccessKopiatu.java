package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Sport;
import domain.Team;

public class TestDataAccessKopiatu {
	protected EntityManager db;
	protected EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public TestDataAccessKopiatu() { 

		System.out.println("Creating TestDataAccess instance");

		open();

	}

	public void open() {

		System.out.println("Opening TestDataAccess instance ");

		String fileName = c.getDbFilename();

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}
	
	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else
			return false;
	}

	public Event addEvent(Event ev) {
		System.out.println(">> DataAccessTest: addEvent");
		
		db.getTransaction().begin();
		try {
			db.persist(ev);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}
	
	public Question obtainQuestion(int ev, int q) {
		Event event = db.find(Event.class, ev);

		Vector<Question> questions = event.getQuestions();
		for (Question question :questions) {
			if (question.getQuestionNumber() == q) {
				System.out.println(">> DataAccess: obtainQuestion=>  question= "+question+" betMinimum="+question.getBetMinimum());
				return question;	
			}

		}	
		return null;
	}
	
	public Sport addSport(Event ev, String sport) {
		System.out.println(">> DataAccessTest: addEvent");
		Event event = db.find(Event.class, ev.getEventNumber());
		Sport s = new Sport(sport);
		
		s.addEvent(event);
		db.getTransaction().begin();
		try {
			db.persist(event);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public Question addQuestions(Event ev, String desc, double x) {
		System.out.println(">> DataAccessTest: addEvent");
		Event event = db.find(Event.class, ev.getEventNumber());
		Question q = new Question(desc, x, event);
		
		event.addQuestion(desc, x);
		event.addQuestion(desc, x);
		db.getTransaction().begin();
		try {
			db.persist(event);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return q;
	}
	
	public Quote addQuotes(Event ev, double x, String des, Question q) {
		System.out.println(">> DataAccessTest: addEvent");
		Event event = db.find(Event.class, ev.getEventNumber());
		Quote que = new Quote(x, des);
		
		Vector<Question> questions = event.getQuestions();
		for(Question question:questions) {
			question.addQuote(x, des, q);

		}
		db.getTransaction().begin();
		try {
			db.persist(ev);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return que;
	}
	
	
//	public boolean gertaerakKopiatu(Event e, Date date) {
//		Boolean b=false;
//		Event gertaera = db.find(Event.class, e.getEventNumber());
//		db.getTransaction().begin();
//		
//		
//		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.getDescription()=?1 and ev.getEventDate()=?2",Event.class);   
//		query.setParameter(1,gertaera.getDescription());
//		query.setParameter(2, date);
//		if(query.getResultList().isEmpty()) {
//			b=true;
//			String[] taldeak = gertaera.getDescription().split("-");
//			Team lokala = new Team(taldeak[0]);
//			Team kanpokoa = new Team(taldeak[1]);
//			Event gertKopiatu = new Event(gertaera.getDescription(), date, lokala, kanpokoa);
//			gertKopiatu.setSport(gertaera.getSport());
//			gertaera.getSport().addEvent(gertKopiatu);
//			db.persist(gertKopiatu);
//				for(Question q : gertaera.getQuestions()) {
//					Question que= new Question(q.getQuestion(), q.getBetMinimum(), gertKopiatu);
//					gertKopiatu.listaraGehitu(que);
//					Question galdera = db.find(Question.class, q.getQuestionNumber());
//					db.persist(que);
//					for(Quote k: galdera.getQuotes()) {
//						Quote kuo= new Quote(k.getQuote(), k.getForecast(), que);
//						que.listaraGehitu(kuo);
//						db.persist(kuo);
//					}
//				}
//		}
//		db.getTransaction().commit();
//		return b;
//	}
}
