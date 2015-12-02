/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
import java.awt.Color;
/**
 *
 * @author anton
 */
public class Screen
{
	//private final Pixel[] pixels;
	private final Color[] pixels;
	private final float[] depth;
	private final int width, height;
	public Screen( int w , int h )
	{
		pixels = new Color[ w * h ];
		depth = new float[ w * h ];
		for( int i = 0; i < w * h; i++ )
		{
			pixels[ i ] = new Color( 0 , 0 , 0 );
			depth[ i ] = 1.0f;
		}
		width = w;
		height = h;
	}
	public void clear()
	{
		//Pixel e = new Pixel( 0.0f , 0.0f , 0.0f , 1.0f );
		for( int i = 0; i < width * height; i++ )
		{
			pixels[ i ] = new Color( 0 , 0 , 0 );
			depth[ i ] = 1.0f;
		}
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public Color get( int x , int y )
	{
		return pixels[ x + y * width ];
	}
	public float getDepth( int x , int y )
	{
		return depth[ x + y * width ];
	}
	public void set( Pixel p , int x , int y )
	{
		if( x < width - 10 && x >= 10 && y < height - 10 && y >= 10 )
		{
			float d = getDepth( x , y );
			if( p.d <= d && p.d >= -1.0f && p.d <= 1.0 )
			{
				pixels[ x + y * width ] = new Color( Math.abs( ( int ) ( p.r * 255 ) ) % 256 , Math.abs( ( int ) ( p.g * 255 ) ) % 256 , Math.abs( ( int ) ( p.b * 255 ) ) % 256 );
				depth[ x + y * width ] = p.d;
			}
		}
	}
	/*public void set( Pixel p , Float2 tx )
	 {
	 int x = ( int )( ( 0.5f * tx.x + 0.5f ) * width );
	 int y = ( int )( ( 0.5f * tx.y + 0.5f ) * height );
	 set(  )
	 }*/
}
