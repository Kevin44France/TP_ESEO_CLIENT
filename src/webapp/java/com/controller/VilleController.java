package com.controller;


import com.model.Ville;
import com.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller

public class VilleController {
    @Autowired
    ApiService apiService;

    @GetMapping(value = "/distance")
    public String get(final Model model) {
        List<Ville> villes = apiService.getVillesData();
        model.addAttribute("villes", villes);
        return "distance";
    }

    @GetMapping("/villes")
    public String getVillesPage(final Model model) {
        List<Ville> villes = apiService.getVillesData();
        model.addAttribute("villes", villes);
        return "villes";
    }

    @GetMapping("/ville/{codeCommune}")
    public String GetVille(@PathVariable String codeCommune, Model model) {
        Ville ville = apiService.getVilleData(codeCommune);
        model.addAttribute("ville", ville);
        return "ville";
    }

    @PostMapping("/ville/{codeCommune}")
    public String modifyVille(@PathVariable String codeCommune, Model model,
                              HttpServletRequest request) {
        String nomCommune = request.getParameter("nom-commune");
        String codePostal = request.getParameter("code-postal");
        String libelle = request.getParameter("libelle");
        String ligne = request.getParameter("ligne");
        if (!nomCommune.equals("")) {
            ApiService.modifyNomCommune(codeCommune, nomCommune);
        }
        if (!codePostal.equals("")) {
            ApiService.modifyCodePostal(codeCommune, codePostal);
        }
        if (!libelle.equals("")) {
            ApiService.modifyLibelle(codeCommune, libelle);
        }
        if (!ligne.equals("")) {
            ApiService.modifyLigne(codeCommune, ligne);
        }
        Ville ville = apiService.getVilleData(codeCommune);
        model.addAttribute("ville", ville);
        return "ville";
    }


}
