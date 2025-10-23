package com.vertdrop.MainApp;

import com.vertdrop.entities.Colis;
import com.vertdrop.entities.Livreur;
import com.vertdrop.entities.StatusColis;
import com.vertdrop.services.ColisService;
import com.vertdrop.services.LivreurService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        LivreurService livreurService = (LivreurService) context.getBean("livreurService");
        ColisService colisService = (ColisService) context.getBean("colisService");

        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        do {
            System.out.println("\n====== SMART DELIVERY SYSTEM ======");
            System.out.println("1️⃣  Ajouter un livreur");
            System.out.println("2️⃣  Lister les livreurs");
            System.out.println("3️⃣  Ajouter un colis");
            System.out.println("4️⃣  Lister tous les colis");
            System.out.println("5️⃣  Lister les colis d’un livreur");
            System.out.println("6️⃣  Mettre à jour le statut d’un colis");
            System.out.println("7️⃣  Quitter");
            System.out.println("===================================");
            System.out.print("👉 Entrez votre choix : ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ Entrée invalide !");
                scanner.nextLine();
                continue;
            }
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nom du livreur : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Véhicule : ");
                    String vehicule = scanner.nextLine();
                    System.out.print("Téléphone : ");
                    String tel = scanner.nextLine();

                    Livreur livreur = new Livreur(nom, prenom, vehicule, tel);
                    livreurService.save(livreur);
                    System.out.println("✅ Livreur ajouté avec succès !");
                    break;

                case 2:
                    List<Livreur> livreurs = livreurService.findAll();
                    System.out.println("\n📋 Liste des livreurs :");
                    livreurs.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Nom du destinataire : ");
                    String destinataire = scanner.nextLine();
                    System.out.print("Adresse : ");
                    String adresse = scanner.nextLine();
                    System.out.print("Poids : ");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("❌ Poids invalide !");
                        scanner.nextLine();
                        break;
                    }
                    double poids = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("ID du livreur assigné : ");
                    if (!scanner.hasNextLong()) {
                        System.out.println("❌ ID invalide !");
                        scanner.nextLine();
                        break;
                    }
                    Long livreurId = scanner.nextLong();
                    scanner.nextLine();

                    Livreur livreurAssign = livreurService.findById(livreurId);
                    if (livreurAssign == null) {
                        System.out.println("❌ Livreur introuvable !");
                        break;
                    }

                    Colis colis = new Colis(destinataire, adresse, poids, StatusColis.PREPARATION, livreurAssign);
                    colisService.saveUnique(colis);
                    System.out.println("📦 Colis ajouté avec succès !");
                    break;

                case 4:
                    List<Colis> colisList = colisService.findAll();
                    System.out.println("\n📦 Liste des colis :");
                    colisList.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("ID du livreur : ");
                    if (!scanner.hasNextLong()) {
                        System.out.println("❌ ID invalide !");
                        scanner.nextLine();
                        break;
                    }
                    Long idLivreur = scanner.nextLong();
                    scanner.nextLine();

                    Livreur liv = livreurService.findById(idLivreur);
                    if (liv == null) {
                        System.out.println("❌ Livreur non trouvé !");
                        break;
                    }
                    List<Colis> colisByLivreur = colisService.findByLivreurId(liv.getId());
                    System.out.println("\n🚚 Colis de " + liv.getNom() + " :");
                    colisByLivreur.forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("ID du colis : ");
                    if (!scanner.hasNextLong()) {
                        System.out.println("❌ ID invalide !");
                        scanner.nextLine();
                        break;
                    }
                    Long colisId = scanner.nextLong();
                    scanner.nextLine();

                    Colis colisToUpdate = colisService.findById(colisId);
                    if (colisToUpdate == null) {
                        System.out.println("❌ Colis introuvable !");
                        break;
                    }

                    System.out.println("Statuts possibles : PREPARATION, EN_TRANSIT, LIVRE");
                    System.out.print("Nouveau statut : ");
                    String statut = scanner.nextLine().toUpperCase();

                    try {
                        StatusColis newStatus = StatusColis.valueOf(statut);
                        colisToUpdate.setStatus(newStatus);
                        colisService.save(colisToUpdate);
                        System.out.println("✅ Statut mis à jour avec succès !");
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Statut invalide !");
                    }
                    break;

                case 7:
                    System.out.println("👋 Au revoir !");
                    break;

                default:
                    System.out.println("❌ Choix invalide !");
                    break;
            }
        } while (choix != 7);

        scanner.close();
    }
}
