package shopping_cart.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;







public class PaginationResultat<T> {
	
	private int enregistrementsTotal;
	private int pageActuel;
	private List<T>liste;
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
		
		boolean db_A_Un_enregistrement = rScrollableResults.first();
		
		if (db_A_Un_enregistrement) {
			// scroll vers la position
			db_A_Un_enregistrement = rScrollableResults.scroll(depuisEnregistrementIndex);
			
			if (db_A_Un_enregistrement) {
				do {
					T enregistrement = (T) rScrollableResults.get(0);
					resultats.add(enregistrement);
					
				} while (rScrollableResults.next()
						&& rScrollableResults.getRowNumber() >= depuisEnregistrementIndex
						&& rScrollableResults.getRowNumber() < maxEnregistrementIndex);
			}
		
			//dernière enregistrement
			rScrollableResults.last();
		}
		//Total d'enregistrments
		this.enregistrementsTotal = rScrollableResults.getRowNumber()+1;
		this.pageActuel = pageIndex +1 ;
		this.liste = resultats;
		this.resultatMax = resultatMax;
		
		this.pagesTotal = (this.enregistrementsTotal / this.resultatMax) + 1 ;
		this.maxNavigationPage = maxNavigationPage;
		
	}
	

}
