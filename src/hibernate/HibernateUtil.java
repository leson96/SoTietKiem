/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author vinh
 */
public class HibernateUtil {
private static final SessionFactory FACTORY;
    static {
        //try {
            Configuration configure = new Configuration();
            configure.configure("/hibernate/hibernate.cfg.xml");
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configure.getProperties());
            FACTORY = configure.buildSessionFactory(builder.build());
//        } catch (Exception e) {
//            System.err.println("123");
//        }
        
    }
public static SessionFactory getSessionFactory() {
    return FACTORY;
    }
}