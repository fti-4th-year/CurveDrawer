package curvedrawer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author anton
 */
public class Int2
{
	public int x;
	public int y;
	public Int2( int x , int y )
	{
		this.x = x;
		this.y = y;
	}
	public Int2()
	{
		this.x = 0;
		this.y = 0;
	}
	public void copyIn( Int2 a )
	{
		this.x = a.x;
		this.y = a.y;
	}
	public Int2 add( Int2 a )
	{
		return new Int2( a.x + x , a.y + y );
	}
	public Int2 copy()
	{
		return new Int2( x , y );
	}
	public Int2 sub( Int2 a )
	{
		return new Int2( x - a.x , y - a.y );
	}
	public int mod2()
	{
		return x * x + y * y;
	}
	public int dist2( Int2 a )
	{
		return this.sub( a ).mod2();
	}
	public boolean isIn( Int2 pos , Int2 size )
	{
		return this.x < pos.x + size.x && this.x > pos.x && this.y < pos.y + size.y && this.y > pos.y;
	}
	public boolean isInBox( Int2 pos , int size )
	{
		return Math.abs( this.x - pos.x ) < size && Math.abs( this.y - pos.y ) < size;
	}
	public Int2 lperp()
	{
		return new Int2( -y , x );
	}
	public int dot( Int2 a )
	{
		return a.x * x + a.y * y;
	}
	public Int2 mul( int i )
	{
		return new Int2( x * i , y * i );
	}
	public Int2 div( int i )
	{
		return new Int2( ( int )( ( double )x / i ) , ( int )( ( double )y / i ) );
	}
	public int mod()
	{
		return ( int )Math.sqrt( ( double )mod2() );
	}
}
