package com.vertdrop.MainApp;

import com.vertdrop.entities.Livreur;
import com.vertdrop.services.LivreurService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        LivreurService service = (LivreurService) context.getBean("livreurService");

        // 1️⃣ Create a Livreur
        Livreur l1 = new Livreur("Houssam", "Bensaltana", "Moto", "0612345678");
        service.save(l1);
        System.out.println("Saved: " + l1);

        // 2️⃣ List all Livreurs
        List<Livreur> allLivreurs = service.findAll();
        System.out.println("All Livreurs: " + allLivreurs);

        // 3️⃣ Find by ID
        Livreur found = service.findById(l1.getId());
        System.out.println("Found by ID: " + found);

        // 4️⃣ Delete by ID
        service.deleteById(l1.getId());
        System.out.println("Deleted Livreur with ID: " + l1.getId());
    }
}
