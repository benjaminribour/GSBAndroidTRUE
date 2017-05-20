package com.example.ben.projetgsb;

import java.util.ArrayList;

public class Protocole {
	
	private int numeroProtocole;
	private ArrayList<GlycemieInsuline> lesGlycemieInsuline;
	
	public Protocole(int unNumero)	{
		this.numeroProtocole = unNumero;
		lesGlycemieInsuline = new ArrayList<GlycemieInsuline>();
	}
	
	public int getNumeroProtocole()	{
		return this.numeroProtocole;
	}
	
	public void ajouter(GlycemieInsuline uneGlycemieInsuline)	{
		this.lesGlycemieInsuline.add(uneGlycemieInsuline);
	}
	
	/*public int insuline(double uneGlycemie)	{
		//A écrire
		//Renvoie le nombre d'unités d'insuline en fonction de la glycémie
		int ret=0;
		for(GlycemieInsuline G : lesGlycemieInsuline) {
			if (G.getGlycemieInf() >= uneGlycemie && G.getGlycemieSup() < uneGlycemie) {
				if (G.getGlycemieInf() == 2.5 && G.getGlycemieSup() == 0)
					ret = (insuline(uneGlycemie));
			}
		}
		return (ret);
	}*/

	public Integer insuline(double uneGlycemie)	{
		int retour =0;
		for(GlycemieInsuline g: lesGlycemieInsuline){
			double inf = g.getGlycemieInf(); //1.8
			double sup = g.getGlycemieSup(); //2.1
			//1.9
			if((uneGlycemie > inf && uneGlycemie <= sup) || (uneGlycemie >= g.getGlycemieInf() && 0==sup)){
				retour = g.getInsuline();
				//retour = g.getInsuline();
			}
			/*else if(){

				retour = g.getInsuline();

			}*/
		}
		return retour;
	}
}
