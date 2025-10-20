
package com.vertdrop.MainApp;

import com.vertdrop.entities.Colis;
import com.vertdrop.entities.Livreur;
import com.vertdrop.services.ColisService;
import com.vertdrop.services.LivreurService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ColisService colisService = context.getBean(ColisService.class);
        LivreurService livreurService = context.getBean(LivreurService.class);

        Livreur livreur = livreurService.save(new Livreur("Ali", "Benz", "Van", "0611122233"));

        Colis colis1 = new Colis("Ahmed", "123 Main St", 5.0, "Préparation", livreur);
        colis1 = colisService.save(colis1);
        System.out.println("Saved Colis: " + colis1);

        System.out.println("All Colis: " + colisService.findAll());

        Colis foundColis = colisService.findById(colis1.getId());
        System.out.println("Found Colis: " + foundColis);

        List<Colis> colisDeAli = colisService.findByLivreurId(livreur.getId());
        System.out.println("Colis assigned to Ali: " + colisDeAli);


        ((ClassPathXmlApplicationContext) context).close();
    }
}
