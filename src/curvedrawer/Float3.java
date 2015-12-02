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
public class Float3
{
	public float x;
	public float y;
	public float z;
	public Float3( float x , float y , float z )
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Float3()
	{
		this( 0.0f , 0.0f , 0.0f );
	}
	public Float3( float[] data )
	{
		this( data[ 0 ] , data[ 1 ] , data[ 2 ] );
	}
	public void copyIn( Float3 a )
	{
		this.x = a.x;
		this.y = a.y;
		this.z = a.z;
	}
	public float[] toArr()
	{
		return new float[]{ x , y , z };
	}
	public List<Float> toList()
	{
		return Arrays.asList( x , y , z );
	}
	public Float3 add( Float3 a )
	{
		return new Float3( a.x + x , a.y + y , a.z + z );
	}
	public Float3 copy()
	{
		return new Float3( x , y , z );
	}
	public float dot( Float3 a )
	{
		return a.x * x + a.y * y + a.z * z;
	}
	public Float3 mul( float i )
	{
		return new Float3( x * i , y * i , z * i );
	}
	public Float3 sub( Float3 a )
	{
		return add( a.mul( -1 ) );
	}
	public float mod2()
	{
		return dot( this );
	}
	public float dist2( Float3 a )
	{
		return this.sub( a ).mod2();
	}
	public Float3 div( float i )
	{
		return this.mul(  1.0f / i );
	}
	public float mod()
	{
		return ( float )Math.sqrt( ( double )mod2() );
	}
	public Float3 norm()
	{
		return this.div( this.mod() );
	}
	public Float3 vecx( Float3 v )
	{
		return new Float3( y * v.z - z * v.y , -x * v.z + z * v.x , x * v.y - y * v.x );
	}
}