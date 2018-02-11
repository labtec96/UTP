package zad2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@FunctionalInterface
interface MousePressListener extends MouseListener
{
	void mouseClicked(MouseEvent e);
	@Override
	default void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	default void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	default void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	default void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}
