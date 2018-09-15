package ecommerce_panier.controlleur;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ecommerce_panier.dao.CommandeDAO;
import ecommerce_panier.dao.ProduitDAO;
import ecommerce_panier.entity.Produit;
import ecommerce_panier.model.InformationClient;
import ecommerce_panier.model.InformationPanier;
import ecommerce_panier.model.InformationProduit;
import ecommerce_panier.model.PaginationResultat;
import ecommerce_panier.util.Utilitaires;
import ecommerce_panier.validateur.InformationClientValidateur;

@Controller
//Permet les transactions hibernate
@Transactional
@EnableWebMvc
public class ControlleurPrincipal {

	
	@Autowired
	private CommandeDAO commandeDAO;
	
	@Autowired
	private ProduitDAO produitDAO;
	
	@Autowired
	private InformationClientValidateur informationClientValidateur;
	
	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object cible = dataBinder.getTarget();
		if (cible == null) {
			return;
		}
		System.out.println("Cible= " + cible );
        // Pour le formualaire panier.
        // (@ModelAttribute("formulairePanier") @Validated InformationPanier formailairePanier)
        if (cible.getClass() == InformationPanier.class) {
 
        }
        //Pour le formulaire client.
        // (@ModelAttribute("formulaireClient") @Validated InformationClient
        // formulaireClient)
        else if (cible.getClass() == InformationClient.class) {
            dataBinder.setValidator(informationClientValidateur);
        }
 
    }
 
    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }
 
    @RequestMapping("/")
    public String home() {
        return "index";
    }
 
    // Page liste produit.
    @RequestMapping({ "/listeProduit" })
    public String listeProduitGestionnaire(Model model, //
            @RequestParam(value = "nom", defaultValue = "") String commeNom,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
 
        PaginationResultat<InformationProduit> result = produitDAO.requetteProduit(page, //
                maxResult, maxNavigationPage, commeNom);
 
        model.addAttribute("paginationProduits", result);
        return "listeProduit";
    }
 
    @RequestMapping({ "/acheterProduit" })
    public String listeProduitGestionnaire(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
 
        Produit produit = null;
        if (code != null && code.length() > 0) {
            produit = produitDAO.findProduit(code);
        }
        if (produit != null) {
 
            // Cart info stored in Session.
            InformationPanier informationPanier = Utilitaires.getInformationPanierDansLaSession(request);
 
            InformationProduit informationProduit = new InformationProduit(produit);
 
            informationPanier.ajouterProduit(informationProduit, 1);
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    @RequestMapping({ "/EcommerceRemoveProduit" })
    public String EnleverProduitGestionnaire(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
        Produit produit = null;
        if (code != null && code.length() > 0) {
            produit = produitDAO.findProduit(code);
        }
        if (produit != null) {
 
            // Cart Info stored in Session.
            InformationPanier informationPanier = Utilitaires.getDerniereCommandePanierDansSession(request);
 
            InformationProduit informationProduit = new InformationProduit(produit);
 
            informationPanier.supprimeProduit(informationProduit);
 
        }
        // Redirection vers la page du site ecommerce
        return "redirect:/ecommerce";
    }
 
    // POST: Mise à jour de la quantité de produit dans le panier.
    @RequestMapping(value = { "/ecommerce" }, method = RequestMethod.POST)
    public String eccommerceMiseàJourQuantité(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("formulairePanierJSP") InformationPanier formulairePanier) {
 
        InformationPanier informationPanier = Utilitaires.getDerniereCommandePanierDansSession(request);
        informationPanier.miseAJourQuantité(formulairePanier);
 
        // Redirection vers la page Ecommerce.
        return "redirect:/shoppingCart";
    }
 
    // GET: Montrer le panier
    @RequestMapping(value = { "/ecommerce" }, method = RequestMethod.GET)
    public String gestionnaireEccommerce(HttpServletRequest request, Model model) {
        InformationPanier monPanier = Utilitaires.getDerniereCommandePanierDansSession(request);
 
        model.addAttribute("formulairePanierJSP", monPanier);
        return "shoppingCart";
    }
 
    // GET: Entrer les informations du client.
    @RequestMapping(value = { "/panierClient" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
 
        InformationPanier informationPanier = Utilitaires.getDerniereCommandePanierDansSession(request);
      
        // panier est vide ?.
        if (informationPanier.isEmpty()) {
             
            // Redirection vers la page principale.
            return "redirect:/ecommerce";
        }
 
        InformationClient informationClient = informationPanier.getInformationClient();
        if (informationClient == null) {
            informationClient = new InformationClient();
        }
 
        model.addAttribute("formulaireClient", informationClient);
 
        return "panierClient";
    }
 
    // POST: sauvegarder les informations client
    @RequestMapping(value = { "/panierClient" }, method = RequestMethod.POST)
    public String sauvegardeClient(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("formulaireClientJSP") @Validated InformationClient formulaireClient, //
            BindingResult resultat, //
            final RedirectAttributes redirectAttributes) {
  
        // Si erreurs.
        if (resultat.hasErrors()) {
            formulaireClient.setValide(false);
            // Redirection pour ré entrer les informations client.
            return "panierClient";
        }
 
        formulaireClient.setValide(true);
        InformationPanier informationPanier = Utilitaires.getDerniereCommandePanierDansSession(request);
 
        informationPanier.setInformationClient(formulaireClient);
 
        // Redirection vers la page de confirmation.
        return "redirect:/ecommerceConfirmation";
    }
 
    // GET: Verification du panier et confirmer.
    @RequestMapping(value = { "/ecommerceConfirmation" }, method = RequestMethod.GET)
    public String verificationPanierConfirmation(HttpServletRequest request, Model model) {
        InformationPanier informationPanier = Utilitaires.getDerniereCommandePanierDansSession(request);
 
        // Panier n'a pas de produit
        if (informationPanier.isEmpty()) {
            //Redurection vers la page panier
            return "redirect:/panier";
        } else if (!informationPanier.isClientValide()) {
            // Entrer les informations Client.
            return "redirect:/panierClient";
        }
 
        return "shoppingCartConfirmation";
    }
 
    // POST: Sauvegarde panier (Save).
    @RequestMapping(value = { "/confirmationPanier" }, method = RequestMethod.POST)
    // Eviter UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String sauvegardeConfirmationPanier(HttpServletRequest request, Model model) {
        InformationPanier informationPanier = Utilitaires.getDerniereCommandePanierDansSession(request);
 
        // Panier n'a pas de produit ?.
        if (informationPanier.isEmpty()) {
            // Redirection vers la page principale.
            return "redirect:/ecommerce";
        } else if (!informationPanier.isClientValide()) {
            // Entrer les informations client.
            return "redirect:/panierClient";
        }
        try {
            commandeDAO.sauvegardeCommande(informationPanier);
        } catch (Exception e) {
            // Propagation.NEVER?
            return "confirmationPanier";
        }
        // Enlever panier dans la session.
        Utilitaires.removePanierDansSession(request);
         
        // Sauvegarde le dernier panier de la session
        Utilitaires.enregistrerDerniereCommandePanierDansSession(request, informationPanier);
 
        // Redirection vers la page
        return "redirect:/shoppingCartFinalize";
    }
 
    @RequestMapping(value = { "/finalisationPanier" }, method = RequestMethod.GET)
    public String finalisePanier(HttpServletRequest request, Model model) {
 
        InformationPanier derniereCommandePanier = Utilitaires.getDerniereCommandePanierDansSession(request);
 
        if (derniereCommandePanier == null) {
            return "redirect:/ecommerce";
        }
 
        return "finalisationPanier";
    }
 
    @RequestMapping(value = { "/imageProduit" }, method = RequestMethod.GET)
    public void imageProduit(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("code") String code) throws IOException {
        Produit produit = null;
        if (code != null) {
            produit = this.produitDAO.findProduit(code);
        }
        if (produit != null && produit.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(produit.getImage());
        }
        response.getOutputStream().close();
    }
     
}