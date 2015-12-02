/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
/**
 *
 * @author anton
 */
public class LineDrawer
{
	static void drawLine( Screen screen , Int2 p1 , Int2 p2 , Pixel px1 , Pixel px2 )
	{
		float dist = new Float2( p1.x , p1.y ).dist( new Float2( p2.x , p2.y ) );
		screen.set( px1 , p1.x , p1.y );
		if( p1.x == p2.x && p1.y == p2.y )
		{
			return;
		}
		if( Math.abs( p2.x - p1.x ) > Math.abs( p2.y - p1.y ) )
		{
			final float k = ( float ) ( p2.y - p1.y ) / Math.abs( p2.x - p1.x );
			float err = 0.0f;
			int i = p1.y;
			int dx = p2.x > p1.x ? 1 : -1;
			for( int j = p1.x; j != p2.x; j += dx )
			{
				i = p1.y + ( int ) Math.floor( err );
				float t = new Float2( p1.x , p1.y ).dist( new Float2( i , j ) );
				screen.set( px1.lerp( px2 , t ) , j , i );
				err += k;
			}
		} else
		{
			final float k = ( float ) ( p2.x - p1.x ) / Math.abs( p2.y - p1.y );
			float err = 0.0f;
			int i = p1.x;
			int dx = p2.y > p1.y ? 1 : -1;
			for( int j = p1.y; j != p2.y; j += dx )
			{
				i = p1.x + ( int ) Math.floor( err );
				float t = new Float2( p1.x , p1.y ).dist( new Float2( i , j ) );
				screen.set( px1.lerp( px2 , t ) , i , j );
				err += k;
			}
		}
	}
}
