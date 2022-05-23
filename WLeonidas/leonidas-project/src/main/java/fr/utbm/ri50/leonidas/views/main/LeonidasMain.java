
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

			@Override
			public void run()
				{
				LeonidasFrame frame = new LeonidasFrame();
				frame.setVisible(true);
				}
			});
		}

	}
