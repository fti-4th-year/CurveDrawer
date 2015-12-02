/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
import java.util.List;
/**
 *
 * @author anton
 */
public class Mat4
{
	public final Float[] m = new Float[ 16 ];
	public Mat4( Float[] data )
	{
		System.arraycopy( data , 0 , m , 0 , 16 );
	}
	public Mat4()
	{
		for( int i = 0; i < 16; i++ )
		{
			m[ i ] = 0.0f;
		}
		for( int j = 0; j < 4; j++ )
		{
			m[ j * 4 + j ] = 1.0f;
		}
	}
	public Mat4( Float4 a , Float4 b , Float4 c , Float4 d )
	{
		System.arraycopy( a.toArr() , 0 , m , 0 , 4 );
		System.arraycopy( b.toArr() , 0 , m , 4 , 4 );
		System.arraycopy( c.toArr() , 0 , m , 8 , 4 );
		System.arraycopy( d.toArr() , 0 , m , 12 , 4 );
	}
	public Mat4 setRow( int i , Float4 a )
	{
		System.arraycopy( a.toArr() , 0 , m , i * 4 , 4 );
		return this;
	}
	public Float4 row( int i )
	{
		return new Float4( m[ i * 4 ] , m[ i * 4 + 1 ] , m[ i * 4 + 2 ] , m[ i * 4 + 3 ] );
	}
	public Float4 col( int i )
	{
		return new Float4( m[ i ] , m[ i + 4 ] , m[ i + 8 ] , m[ i + 12 ] );
	}
	public Float4 mul( Float4 a )
	{
		float[] out = new float[ 4 ];
		for( int i = 0; i < 4; i++ )
		{
			out[ i ] = row( i ).dot( a );
		}
		return new Float4( out );
	}
	public Mat4 transp()
	{
		Float[] out = new Float[ 16 ];
		for( int i = 0; i < 4; i++ )
		{
			for( int j = 0; j < 4; j++ )
			{
				out[ i * 4 + j ] = m[ j * 4 + i ];
			}
		}
		return new Mat4( out );
	}
	public Mat4 mul( Mat4 a )
	{
		Float[] out = new Float[ 16 ];
		for( int i = 0; i < 4; i++ )
		{
			for( int j = 0; j < 4; j++ )
			{
				out[ i * 4 + j ] = row( i ).dot( a.col( j ) );
			}
		}
		return new Mat4( out );
	}
	public void print()
	{
		for( int i = 0; i < 4; i++ )
		{
			for( int j = 0; j < 4; j++ )
			{
				System.out.print( m[ i * 4 + j ] + " " );
			}
			System.out.println();
		}
	}
}
