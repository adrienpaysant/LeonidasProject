package fr.utbm.ri50.leonidas.views.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import fr.utbm.ri50.leonidas.tools.layout.JCenter;
import fr.utbm.ri50.leonidas.tools.layout.JCenterH;

public class MainContener extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public MainContener()
		{

		super(BoxLayout.Y_AXIS);
		add(new JCenterH(new JLabel("toolbar")));
//		add((new JCenterH( new JLabel("dummy placeholder H-centered"))));//TODO TO REMOVE
//		add((new JCenterV( new JLabel("dummy placeholder V-centered"))));//TODO TO REMOVE
		add((new JCenter( new JLabel("dummy placeholder centered"))));//TODO TO REMOVE

		geometry();
		control();
		appearance();
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

	private void geometry()
		{
		// TODO
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs

	// Tools


	}
