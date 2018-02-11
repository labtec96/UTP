package zad1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main extends JFrame implements ActionListener
{
	HashMap<Integer, Lock> mapaLockow = new HashMap<Integer, Lock>();

	JPanel panelStop;
	JPanel panelText;
	JPanel panelWatki;

	JButton stop;

	JScrollPane sbrText;
	JTextArea textArea;
	int liczbaWatkow = 5;
	JButton[] buttonWatki = new JButton[liczbaWatkow];
	int ktoryWatek=0;
	boolean czyWcisniety=false;
	Main()
	{

		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelStop = new JPanel();
		stop = new JButton("STOP");
		stop.setPreferredSize(new Dimension(570, 30));
		panelStop.add(stop);

		panelText = new JPanel();
		textArea = new JTextArea();
		sbrText = new JScrollPane(textArea);
		sbrText.setPreferredSize(new Dimension(570, 450));
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelText.add(sbrText);

		panelStop = new JPanel();
		stop = new JButton("STOP");
		stop.addActionListener(new ActionListener() 
	        {
	            public void actionPerformed(ActionEvent e) 
	            {
	            	for (int i =0; i < liczbaWatkow; i++)
	            	{
	            		buttonWatki[i].setEnabled(false);
	            	}
	                exec.shutdownNow();
	                stop.setEnabled(false); 
	            }
	        });
		stop.setPreferredSize(new Dimension(getWidth() - 30, 30));
		panelStop.add(stop);

		panelWatki = new JPanel();
		for (int i = 1; i <= liczbaWatkow; i++)
		{
			buttonWatki[i - 1] = new JButton("Thread " + Integer.toString(i));
			buttonWatki[i - 1].setPreferredSize(new Dimension(570 / liczbaWatkow, 30));
			buttonWatki[i - 1].addActionListener(this);
			buttonWatki[i - 1].setActionCommand("Start");
			buttonWatki[i - 1].setName(Integer.toString(i));
			buttonWatki[i - 1].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if(czyWcisniety) 
                    {
                      for (int i=1; i <=liczbaWatkow; i++)
                    	 if(buttonWatki[i-1] == e.getComponent())
                    	 {
                    		 int s = i-1;
                    		System.out.println("usuwam dla " + s+ " ");
	                    	buttonWatki[i - 1].setEnabled(false);
	                      	buttonWatki[i - 1].setText("CANCELLED");
	                        task.cancel(true);
	                    	textArea.append("Thread "+i+": Cancelledze !\n");
                    	 }
                        
                    }
                }              
            });
			buttonWatki[i - 1].addKeyListener(new KeyAdapter()
			{
				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_C)
					{
						czyWcisniety = true;
					}
				}

				public void keyReleased(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_C)
					{
						czyWcisniety = false;
					}
				}
				public void keyTyped(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_C)
					{
						czyWcisniety = false;
					}
				}
			});
			panelWatki.add(buttonWatki[i - 1]);
		}

		add(panelStop, BorderLayout.NORTH);
		add(panelText, BorderLayout.CENTER);
		add(panelWatki, BorderLayout.SOUTH);
		setVisible(true);
		setResizable(false);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		
		Class[] paramInt = new Class[1];
		paramInt[0] = JButton.class;
		String cmd = e.getActionCommand();
		try
		{
			Method m = this.getClass().getDeclaredMethod("task" + cmd, paramInt);
			m.invoke(this, (JButton) e.getSource());
		} catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	class MyThread implements Callable<Integer>
	{
		private int taskNum, limit;
		public MyThread(int taskNum, int limit)
		{
			this.taskNum = taskNum;
			this.limit = limit;
		}

		int sum = 0;
		@Override
		public Integer call() throws Exception
		{
			mapaLockow.put((int) Thread.currentThread().getId(), new Lock());
			buttonWatki[ktoryWatek].setName(Long.toString(Thread.currentThread().getId()));
			ktoryWatek++;
			int sum = 0;
				do
				{
					synchronized (mapaLockow.get(Integer.parseInt(buttonWatki[taskNum-1].getName())).obj)
					{
						while (mapaLockow.get(Integer.parseInt(buttonWatki[taskNum-1].getName())).czyZablokowane)
							mapaLockow.get(Integer.parseInt(buttonWatki[taskNum-1].getName())).obj.wait();
					}
						int dod = (int) (Math.random() * 101);
						sum += dod;
						textArea.append(
								"Thread " + taskNum + " (limit = " + limit + "):" + dod + "," + "sum = " + sum + '\n');
						Thread.sleep((int) (Math.random() * 1800) + 200);
				} while (limit > sum);
		//	}
			buttonWatki[taskNum-1].setText("Done");
			buttonWatki[taskNum-1].setEnabled(false);
			textArea.append("Thread: " + taskNum + " Done!" + '\n');
			return sum;
		}

	};

	Future<Integer> task;
	ExecutorService exec = Executors.newFixedThreadPool(liczbaWatkow);

	public void taskStart(JButton e)
	{
		int nrWatku = Integer.parseInt(e.getName());
		task = exec.submit(new MyThread(nrWatku, (int) (Math.random() * 2000)));
		e.setActionCommand("Suspend");
		e.setText("Suspend");
	}

	public void taskSuspend(JButton e)
	{
		e.setActionCommand("Continue");
		e.setText("Continue");
		Iterator it = mapaLockow.entrySet().iterator();
		mapaLockow.get(Integer.parseInt(e.getName())).czyZablokowane=true;
	}

	public void taskContinue(JButton e)
	{
		synchronized (mapaLockow.get(Integer.parseInt(e.getName())).obj)
		{
			mapaLockow.get(Integer.parseInt(e.getName())).obj.notify();
			mapaLockow.get(Integer.parseInt(e.getName())).czyZablokowane = false;
		}
		e.setActionCommand("Suspend");
		e.setText("Suspend");
	}

	public static void main(String[] args)
	{

		new Main();
	}

}

class Lock
{
	Object obj;
	boolean czyZablokowane;
	public Lock()
	{
		this.obj = new Object();
		this.czyZablokowane = false;
	}
}
