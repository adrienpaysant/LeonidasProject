
package fr.utbm.ri50.leonidas.tools.layout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class JMargin extends JPanel
	{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JMargin(JComponent comp)
		{
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(comp, BorderLayout.CENTER);
		appearance(comp.getBackground());
		}

	public JMargin(JComponent comp, int margin)
		{
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
		add(comp, BorderLayout.CENTER);
		appearance(comp.getBackground());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void appearance(Color color)
		{
		setBackground(color);
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
