package com.vertdrop.MainApp;

import com.vertdrop.entities.*;
import com.vertdrop.services.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        LivreurService livreurService = (LivreurService) context.getBean("livreurService");
        ColisService colisService = (ColisService) context.getBean("colisService");

        // Create livreur
        Livreur livreur = livreurService.save(new Livreur("Yassine", "Renault", "Kangoo", "0622334455"));

        // Create colis
        Colis colis1 = new Colis("Ahmed", "Rue Hassan II", 4.5, StatusColis.PREPARATION, livreur);
        Colis colis2 = new Colis("Samira", "Bd Mohammed V", 2.0, StatusColis.PREPARATION, livreur);

        colisService.saveUnique(colis1);
        colisService.saveUnique(colis2);

        // List colis for livreur
        List<Colis> colisList = colisService.findByLivreurId(livreur.getId());
        System.out.println("📦 Colis de " + livreur.getNom() + ": " + colisList);

        // Update status of colis
        colis1.setStatus(StatusColis.EN_TRANSIT);
        colisService.save(colis1);
        System.out.println("🚚 Colis en transit: " + colis1);

        colis1.setStatus(StatusColis.LIVRE);
        colisService.save(colis1);
        System.out.println("✅ Colis livré: " + colis1);

        // Try duplicate
        Colis duplicate = new Colis("Ahmed", "Rue Hassan II", 4.5, StatusColis.PREPARATION, livreur);
        colisService.saveUnique(duplicate);
    }
}
