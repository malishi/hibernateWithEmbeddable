package com.marmil.test.hibernate1;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class App 
{
    public static void main( String[] args )
    {
    	
    	Author a1 = new Author();
    	a1.setFirstName("Frank");
    	a1.setMidName("Waldo");
    	a1.setLastName("Cunninger");
    	
    	Author a2 = new Author();
    	a2.setFirstName("Ralph");
    	a2.setMidName("Eric");
    	a2.setLastName("Sadesh");
    	
       Book b1 = new Book();
       b1.setId(1);
       b1.setAuthor(a1);
       b1.setTitle("Macintosh");
       b1.setPageNumbers(420);
       
       Book b2 = new Book();
       b2.setId(2);
       b2.setAuthor(a2);
       b2.setTitle("The List");
       b2.setPageNumbers(93);
       
       
       Configuration c = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Book.class); //no need to mention name since it's default
       ServiceRegistry sreg  = new ServiceRegistryBuilder().applySettings(c.getProperties()).buildServiceRegistry();
       SessionFactory sf = c.buildSessionFactory(sreg);
       Session s= sf.openSession();
       
       Transaction t = s.beginTransaction();
       s.save(b1);
       s.save(b2);
       b1= (Book) s.get(Book.class, 1);
       t.commit();
       System.out.println(b1);
    }
}
