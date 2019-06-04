/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.controller;

import java.awt.Color;
import tk.hausplant.model.Planta;
import tk.hausplant.view.Prancheta;

public class HausPlant {

	/**
	 * HausPlant entry point.
	 */
	public static void main(final String[] args) {
		Planta planta = new Planta();
		Prancheta prancheta = new Prancheta(planta, Color.white);
		prancheta.showWindow();
	}

}
