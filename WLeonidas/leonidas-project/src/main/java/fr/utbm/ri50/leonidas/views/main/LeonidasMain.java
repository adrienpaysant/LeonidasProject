
package fr.utbm.ri50.leonidas.views.main;

public class LeonidasMain
	{

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		java.awt.EventQueue.invokeLater(new Runnable()
			{

			public void run()
				{
				JLeonidasFrame frame = new JLeonidasFrame();
				frame.setVisible(true);
				}
			});
		}

	}