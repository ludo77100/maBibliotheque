package org.ludo.bibliotheque.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ludo.bibliotheque.BibliothequeApplication;
import org.ludo.bibliotheque.dto.LivreDto;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controlleur Livre du microservice
 */
@RestController
public class LivreController {

    private static final Logger logger = LogManager.getLogger(BibliothequeApplication.class);

    @Autowired
    LivreService livreService ;

    /**
     * Controlleur pour récupérer la liste de tout les livres disponible
     * @return une liste de tous les livres
     */
    @ApiOperation(value = "Retourne la liste de tous les livres")
    @GetMapping(value = "/liste")
    public List<Livre> listeLivre(){
        logger.debug("Appel controlleur listeLivre");

        return livreService.findAll();
    }

    /**
     * Controlleur pour effectuer une recherche sur les livres
     * @param mc mot clé pour effectuer la recherche
     * @return une liste des livres contenant le mot clé
     */
    @ApiOperation(value = "Retourne la liste de tous les livre par une recherche sur le titre")
    @GetMapping(value = "/listeRecherche")
    public List<Livre> listeLivreRecherche(@RequestParam(name = "mc")String mc){
        logger.debug("appel controlleur listeLivreRecherche");

        return livreService.findByTitreContainingIgnoreCase(mc);
    }

    /**
     * Controlleur pour enregistrer un nouveau livre dans la base de données
     * @param livreDto instance du livre à insérer
     * @return enregistrement du nouveau livre
     */
    @ApiOperation(value = "Enregistre un nouveau livre en BDD")
    @PostMapping(value ="/livre/enregistrer")
    public Livre enregistrerNouveauLivre(@RequestBody LivreDto livreDto){
        logger.debug("appel controlleur enregistrerNouveauLivre");

        return livreService.enregistrerNouveauLivre(livreDto);
    }

    /**
     * Controlleur pour modifier un livre dans la base de données
     * @param livreDto instance du livre à modifier
     * @return enregistrement des modifications du livre
     */
    @ApiOperation(value = "Enregistrer les modifications d'un livre")
    @PutMapping(value = "/livre/edition")
    public Livre editionLivre(@RequestBody LivreDto livreDto){
        logger.debug("appel controlleur editionLivre");

        return livreService.editionLivre(livreDto);
    }

    /**
     * Controlleur pour supprimer un livre par son id
     * @param idLivre id du livre à supprimer
     * @return la suppression du livre
     */
    @ApiOperation(value = "Pour supprimer un livre")
    @GetMapping(value = "/livre/supprimer")
    public void supprimerLivre(int idLivre){
        logger.debug("appel controlleur supprimerLivre");

        livreService.supprimerLivre(idLivre);
    }
}