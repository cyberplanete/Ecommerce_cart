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
		
		if (maxNavigationPage < pagesTotal) {
			this.maxNavigationPage = maxNavigationPage;
		}
		
		this.calculPagesNavigation();
	}

	private void calculPagesNavigation() {
		this.navigationPages = new ArrayList<Integer>();
		
		int nouvellePageActuel = this.pageActuel > this.pagesTotal ? this.pagesTotal : this.pageActuel;
		
		int début = nouvellePageActuel - this.maxNavigationPage / 2;
		int fin = nouvellePageActuel + this.maxNavigationPage /2;
		
		//première page
		navigationPages.add(1);
		if (début > 1) {
			navigationPages.add(-1);
		}
		
		for(int i = début; i < fin; i++) {
			if (i>1 &&i<this.pagesTotal) {
				navigationPages.add(i);
			}

		}
		
		if (fin < this.pagesTotal - 2) {
			navigationPages.add(-1);
			
		}
		//dernière Page
		navigationPages.add(this.pagesTotal);
		
	}

	public int getEnregistrementsTotal() {
		return enregistrementsTotal;
	}

	public int getPageActuel() {
		return pageActuel;
	}

	public List<T> getListe() {
		return liste;
	}

	public int getResultatMax() {
		return resultatMax;
	}

	public int getPagesTotal() {
		return pagesTotal;
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}
	

	
	
}
