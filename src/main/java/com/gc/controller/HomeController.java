package com.gc.controller;

import com.gc.models.CustomersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping("/")

    public ModelAndView helloWorld() {
        return new
                ModelAndView("welcome", "message", "Hello World");

    }

    public ArrayList<CustomersEntity> getAllCustomers() {

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFact = cfg.buildSessionFactory();

        Session selectCustomers = sessionFact.openSession();

        selectCustomers.beginTransaction();

        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);

        ArrayList<CustomersEntity> customerList = (ArrayList<CustomersEntity>) c.list();

        return customerList;

    }

    @RequestMapping("welcome")

    public ModelAndView helloWorld2() {

        ArrayList<CustomersEntity> customerList = getAllCustomers();

        return new
                ModelAndView("welcome2", "cList", customerList);

    }

    @RequestMapping("delete")
    public ModelAndView deleteCustomer(@RequestParam("id") String id) {
        // temp will store info for the object that we want to delete
        CustomersEntity temp = new CustomersEntity();
        temp.setCustomerId(id);

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory fact = cfg.buildSessionFactory();

        Session customers = fact.openSession();
        customers.beginTransaction();

        customers.delete(temp); //delete the object from the list

        customers.getTransaction().commit(); //delete the row from the database

        ArrayList<CustomersEntity> customerList = getAllCustomers();

        return new
                ModelAndView("welcome2", "cList", customerList);
    }

    @RequestMapping("searchByCity")
    public ModelAndView searchByCity(@RequestParam("city") String cityName) {

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFact = cfg.buildSessionFactory();

        Session selectCustomers = sessionFact.openSession();

        selectCustomers.beginTransaction();

        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);

        c.add(Restrictions.like("city", "%" + cityName + "%"));

        ArrayList<CustomersEntity> customerList = (ArrayList<CustomersEntity>) c.list();

        return new
                ModelAndView("welcome2", "cList", customerList);

    }

    @RequestMapping("searchByCountry")
    public ModelAndView searchByCountry(@RequestParam("country") String countryName) {

        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFact = cfg.buildSessionFactory();

        Session selectCustomers = sessionFact.openSession();

        selectCustomers.beginTransaction();

        Criteria c = selectCustomers.createCriteria(CustomersEntity.class);

        c.add(Restrictions.like("country", "%" + countryName + "%"));

        ArrayList<CustomersEntity> customerList = (ArrayList<CustomersEntity>) c.list();

        return new
                ModelAndView("welcome2", "cList", customerList);

    }



}
