package fr.utbm.ri50.leonidas.tools.layout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class JCenterV extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JCenterV(JComponent comp)
		{
		super(BoxLayout.Y_AXIS);
		add(createVerticalGlue());
		add(comp);
		add(createVerticalGlue());
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



	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools


	}
