/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
/**
 *
 * @author anton
 */
public class PolyLine
{
	private final List< Float3> points = new ArrayList();
	private Bound bound = new Bound();
	public void pushPoint( Float3 p )
	{
		insertPoint( points.size() , p );
	}
	public List< Integer> getSelected( Object area )
	{
		if( !bound.collide( area ) )
		{
			return null;
		}
		if( area instanceof Ray )
		{
		}
		return null;
	}
	public void insertPoint( int i , Float3 p )
	{
		if( i >= 0 && i <= points.size() )
		{
			points.add( i , p.copy() );
		}
	}
	public void removePoint( int i )
	{
		if( i >= 0 && i < points.size() )
		{
			points.remove( i );
		}
	}
	public void popPoint()
	{
		removePoint( points.size() - 1 );
	}
	public List< Float3> getPointsCopy()
	{
		List< Float3> out = new ArrayList();
		for( Float3 f : points )
		{
			out.add( f.copy() );
		}
		return out;
	}
	public int getPointCount()
	{
		return points.size();
	}
	public void setPointPos( int i , Float3 p )
	{
		points.get( i ).copyIn( p );
	}
	public Float3 getPointPos( int i )
	{
		return points.get( i ).copy();
	}
	public void calcBound()
	{
		bound = new Bound();
		boolean inited = false;
		final int pcount = points.size();
		for( int i = 0; i < pcount; i++ )
		{
			if( !inited )
			{
				bound.init( points.get( i ) , 1 );
				inited = true;
			} else
			{
				bound.grow( points.get( i ) , 1 );
			}
		}
	}
}
