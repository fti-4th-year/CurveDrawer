/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
import java.util.Vector;
/**
 *
 * @author anton
 * @param <PerVertex>
 */
public class PerVertexBuffer< PerVertex >
{
	private final Vector< PerVertex > data = new Vector();
	public PerVertex get( int i )
	{
		return data.get( i );
	}
	public PerVertexBuffer add( PerVertex p )
	{
		data.add( p );
		return this;
	}
	/*public PerVertex[] getArr( int i , int count )
	{
		PerVertex[] out = new PerVertex[ count ];
		for( int j = 0; j < count; j++ )
		{
			out[ j ] = get( i + j );
		}
		return out;
	}*/
	public int size()
	{
		return data.size();
	}
}
