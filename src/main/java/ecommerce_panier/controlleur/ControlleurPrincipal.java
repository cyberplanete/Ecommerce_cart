package ecommerce_panier.controlleur;

import java.lang.annotation.Target;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
    public String listProductHandler(Model model, //
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
    public String listProductHandler(HttpServletRequest request, Model model, //
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
 
    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
        Product product = null;
        if (code != null && code.length() > 0) {
            product = produitDAO.findProduct(code);
        }
        if (product != null) {
 
            // Cart Info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);
 
            ProductInfo productInfo = new ProductInfo(product);
 
            cartInfo.removeProduct(productInfo);
 
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    // POST: Update quantity of products in cart.
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("cartForm") CartInfo cartForm) {
 
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
 
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    // GET: Show Cart
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);
 
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }
 
    // GET: Enter customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
 
        CartInfo cartInfo = Utils.getCartInSession(request);
      
        // Cart is empty.
        if (cartInfo.isEmpty()) {
             
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        }
 
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        if (customerInfo == null) {
            customerInfo = new CustomerInfo();
        }
 
        model.addAttribute("customerForm", customerInfo);
 
        return "shoppingCartCustomer";
    }
 
    // POST: Save customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("customerForm") @Validated CustomerInfo customerForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
  
        // If has Errors.
        if (result.hasErrors()) {
            customerForm.setValid(false);
            // Forward to reenter customer info.
            return "shoppingCartCustomer";
        }
 
        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        cartInfo.setCustomerInfo(customerForm);
 
        // Redirect to Confirmation page.
        return "redirect:/shoppingCartConfirmation";
    }
 
    // GET: Review Cart to confirm.
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
 
        return "shoppingCartConfirmation";
    }
 
    // POST: Send Cart (Save).
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
        try {
            orderDAO.saveOrder(cartInfo);
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            return "shoppingCartConfirmation";
        }
        // Remove Cart In Session.
        Utils.removeCartInSession(request);
         
        // Store Last ordered cart to Session.
        Utils.storeLastOrderedCartInSession(request, cartInfo);
 
        // Redirect to successful page.
        return "redirect:/shoppingCartFinalize";
    }
 
    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {
 
        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
 
        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
 
        return "shoppingCartFinalize";
    }
 
    @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("code") String code) throws IOException {
        Product product = null;
        if (code != null) {
            product = this.produitDAO.findProduct(code);
        }
        if (product != null && product.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(product.getImage());
        }
        response.getOutputStream().close();
    }
     
}