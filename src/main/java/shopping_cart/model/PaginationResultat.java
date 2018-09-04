package shopping_cart.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;







public class PaginationResultat<T> {
	
	private int enregistrementsTotal;
	private int pageActuel;
	private List<T>list;
	private int resultatMax;
	private int pagesTotal;
	
	private int maxNavigationPage;
	
	private List<Integer> navigationPages;
	
	// @page: 1, 2 ...
	public PaginationResultat(Query requette, int page, int resultatMax,int maxNavigationPage) {
		
		final int pageIndex = page -1<0 ? 0 : page - 1;
		int depuisEnregistrementIndex = pageIndex * resultatMax;
		int maxEnregistrementIndex = depuisEnregistrementIndex + resultatMax;
		
		ScrollableResults rScrollableResults = requette.scroll(ScrollMode.SCROLL_INSENSITIVE);
		
		List resultats = new ArrayList<>();
		
		boolean A_Un_Resultat = rScrollableResults.first();
		
		if (A_Un_Resultat) {
			
			A_Un_Resultat = rScrollableResults.scroll(depuisEnregistrementIndex);
		}
		
	}
	

}
