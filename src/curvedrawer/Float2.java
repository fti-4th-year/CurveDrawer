/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author anton
 */
public class Float2
{
	public float x;
	public float y;
	public Float2( float x , float y )
	{
		this.x = x;
		this.y = y;
	}
	public Float2()
	{
		this( 0.0f , 0.0f );
	}
	public Float2( float[] data )
	{
		this( data[ 0 ] , data[ 1 ] );
	}
	public void copyIn( Float2 a )
	{
		this.x = a.x;
		this.y = a.y;
	}
	public float[] toArr()
	{
		return new float[]{ x , y };
	}
	public List<Float> toList()
	{
		return Arrays.asList( x , y );
	}
	public Float2 add( Float2 a )
	{
		return new Float2( a.x + x , a.y + y );
	}
	public Float2 copy()
	{
		return new Float2( x , y );
	}
	public float dot( Float2 a )
	{
		return a.x * x + a.y * y;
	}
	public Float2 mul( float i )
	{
		return new Float2( x * i , y * i );
	}
	public Float2 sub( Float2 a )
	{
		return add( a.mul( -1 ) );
	}
	public float mod2()
	{
		return dot( this );
	}
	public float dist2( Float2 a )
	{
		return this.sub( a ).mod2();
	}
	public float dist( Float2 a )
	{
		return ( float )Math.sqrt( this.sub( a ).mod2() );
	}
	public Float2 div( float i )
	{
		return this.mul(  1.0f / i );
	}
	public float mod()
	{
		return ( float )Math.sqrt( ( double )mod2() );
	}
	public Float2 norm()
	{
		return this.div( this.mod() );
	}
}