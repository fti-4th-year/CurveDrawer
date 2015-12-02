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
public class Mat3
{
	public final Float[] m = new Float[ 9 ];
	public Mat3( Float[] data )
	{
		System.arraycopy( data , 0 , m , 0 , 9 );
	}
	public Mat3()
	{
		for( int i = 0; i < 9; i++ )
		{
			m[ i ] = 0.0f;
		}
		for( int j = 0; j < 3; j++ )
		{
			m[ j * 3 + j ] = 1.0f;
		}
	}
	public Mat3( Float3 a , Float3 b , Float3 c )
	{
		for( int i = 0; i < 9; i++ )
		{
			m[ i ] = 0.0f;
		}
		System.arraycopy( a.toArr() , 0 , m , 0 , 3 );
		System.arraycopy( b.toArr() , 0 , m , 3 , 3 );
		System.arraycopy( c.toArr() , 0 , m , 6 , 3 );
	}
	public Mat3( float rotz )
	{
		for( int i = 0; i < 9; i++ )
		{
			m[ i ] = 0.0f;
		}
		m[ 0 ] = ( float )Math.cos( rotz );
		m[ 1 ] = ( float )Math.sin( rotz );
		m[ 3 ] = -m[ 1 ];
		m[ 4 ] = m[ 0 ];
		m[ 8 ] = 1.0f;
	}
	public void copyIn( Mat3 mat )
	{
		System.arraycopy( m , 0 , mat.m , 0 , 9 );
	}
	public Mat3 setRow( int i , Float3 a )
	{
		System.arraycopy( a.toArr() , 0 , m , i * 3 , 3 );
		return this;
	}
	public Float3 row( int i )
	{
		return new Float3( m[ i * 3 ] , m[ i * 3 + 1 ] , m[ i * 3 + 2 ] );
	}
	public Float3 col( int i )
	{
		return new Float3( m[ i ] , m[ i + 3 ] , m[ i + 6 ] );
	}
	public Float3 mul( Float3 a )
	{
		float[] out = new float[ 3 ];
		for( int i = 0; i < 3; i++ )
		{
			out[ i ] = row( i ).dot( a );
		}
		return new Float3( out );
	}
	public Mat3 transp()
	{
		Float[] out = new Float[ 9 ];
		for( int i = 0; i < 3; i++ )
		{
			for( int j = 0; j < 3; j++ )
			{
				out[ i * 3 + j ] = m[ j * 3 + i ];
			}
		}
		return new Mat3( out );
	}
	public Mat3 mul( Mat3 a )
	{
		Float[] out = new Float[ 9 ];
		for( int i = 0; i < 3; i++ )
		{
			for( int j = 0; j < 3; j++ )
			{
				out[ i * 3 + j ] = row( i ).dot( a.col( j ) );
			}
		}
		return new Mat3( out );
	}
	public void print()
	{
		for( int i = 0; i < 3; i++ )
		{
			for( int j = 0; j < 3; j++ )
			{
				System.out.print( m[ i * 3 + j ] + " " );
			}
			System.out.println();
		}
	}
}
