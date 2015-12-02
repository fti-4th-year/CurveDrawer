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
public class Float4
{
	public float x;
	public float y;
	public float z;
	public float w;
	public Float4( float x , float y , float z , float w )
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	public Float4( Float3 v , float w )
	{
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = w;
	}
	public Float4()
	{
		this( 0.0f , 0.0f , 0.0f , 0.0f );
	}
	public Float4( float[] data )
	{
		this( data[ 0 ] , data[ 1 ] , data[ 2 ] , data[ 3 ] );
	}
	public Float4 proj()
	{
		return this.mul( 1.0f / w );
	}
	public Float3 xyz()
	{
		return new Float3( x , y , z );
	}
	public void copyIn( Float4 a )
	{
		this.x = a.x;
		this.y = a.y;
		this.z = a.z;
		this.w = a.w;
	}
	public  Float[] toArr()
	{
		return ( Float[] )Arrays.asList( x , y , z , w ).toArray();
	}
	public Float4 add( Float4 a )
	{
		return new Float4( a.x + x , a.y + y , a.z + z , a.w + w );
	}
	public Float4 copy()
	{
		return new Float4( x , y , z , w );
	}
	public float dot( Float4 a )
	{
		return a.x * x + a.y * y + a.z * z + a.w * w;
	}
	public Float4 mul( float i )
	{
		return new Float4( x * i , y * i , z * i , w * i );
	}
	public Float4 sub( Float4 a )
	{
		return add( a.mul( -1 ) );
	}
	public float mod2()
	{
		return dot( this );
	}
	public float dist2( Float4 a )
	{
		return this.sub( a ).mod2();
	}
	public Float4 div( float i )
	{
		return this.mul(  1.0f / i );
	}
	public float mod()
	{
		return ( float )Math.sqrt( ( double )mod2() );
	}
	public Float4 norm()
	{
		return this.div( this.mod() );
	}
	public Float4 mul( Mat4 a )
	{
		float[] out = new float[ 4 ];
		for( int i = 0; i < 4; i++ )
		{
			out[ i ] = a.col( i ).dot( this );
		}
		return new Float4( out );
	}
}