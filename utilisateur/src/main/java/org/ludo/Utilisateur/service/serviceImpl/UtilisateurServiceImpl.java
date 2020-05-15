package org.ludo.Utilisateur.service.serviceImpl;

import org.ludo.Utilisateur.dao.UtilisateurRepository;
import org.ludo.Utilisateur.dto.UtilisateurDto;
import org.ludo.Utilisateur.emums.RoleEnum;
import org.ludo.Utilisateur.entities.Utilisateur;
import org.ludo.Utilisateur.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    /**
     * Liste tous les utilisateur
     * @return une liste d'utilisateur
     */
    @Override
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    /**
     * Trouve un utilisateur par son pseudo
     * @param pseudo pseudo de l'utilisateur à trouver
     * @return l'utilisateur
     */
    @Override
    public Utilisateur findByPseudo(String pseudo) {
        return utilisateurRepository.findByPseudo(pseudo);
    }

    /**
     * Permet d'enregitrer un nouvel utilisateur
     * @param utilisateurDto informations sur l'utilisateur à enrgistrer
     * @return un nouvel utilisateur
     */
    @Override
    public Utilisateur enregistrerUtilisateur(UtilisateurDto utilisateurDto) {

        Utilisateur nouvelUtilisateur = new Utilisateur();

        nouvelUtilisateur.setEmail(utilisateurDto.getEmail());
        nouvelUtilisateur.setMotDePass(utilisateurDto.getMotDePass());
        nouvelUtilisateur.setPseudo(utilisateurDto.getPseudo());
        nouvelUtilisateur.grantAuthority(RoleEnum.UTILISATEUR);

        return utilisateurRepository.save(nouvelUtilisateur);
    }

    /**
     * Permet la modification du privilège d'un utilisateur au rôle "utilisateur"
     * @param pseudo pseudo de l'utilisateur à modifier
     * @return l'utilisateur avec son rôle modifier
     */
    @Override
    public Utilisateur privilegeUtilisateur(String pseudo) {

        Utilisateur modificationUtilisateur = utilisateurRepository.findByPseudo(pseudo);

        modificationUtilisateur.grantAuthority(RoleEnum.UTILISATEUR);

        return utilisateurRepository.save(modificationUtilisateur);
    }

    /**
     * Permet la modification du privilège d'un utilisateur au rôle "bibliothequaire"
     * @param pseudo pseudo de l'utilisateur à modifier
     * @return l'utilisateur avec son rôle modifier
     */
    @Override
    public Utilisateur privilegeBibliothecaire(String pseudo) {

        Utilisateur modificationUtilisateur = utilisateurRepository.findByPseudo(pseudo);

        modificationUtilisateur.getUserRoleList().clear();

        modificationUtilisateur.grantAuthority(RoleEnum.UTILISATEUR);
        modificationUtilisateur.grantAuthority(RoleEnum.BIBLIOTHEQUAIRE);

        return utilisateurRepository.save(modificationUtilisateur);
    }

    /**
     * Permet la modification du privilège d'un utilisateur au rôle "admin"
     * @param pseudo pseudo de l'utilisateur à modifier
     * @return l'utilisateur avec son rôle modifier
     */
    @Override
    public Utilisateur privilegeAdministrateur(String pseudo) {

        Utilisateur modificationUtilisateur = utilisateurRepository.findByPseudo(pseudo);

        modificationUtilisateur.getUserRoleList().clear();

        modificationUtilisateur.grantAuthority(RoleEnum.UTILISATEUR);
        modificationUtilisateur.grantAuthority(RoleEnum.BIBLIOTHEQUAIRE);
        modificationUtilisateur.grantAuthority(RoleEnum.ADMIN);

        return utilisateurRepository.save(modificationUtilisateur);
    }
}
