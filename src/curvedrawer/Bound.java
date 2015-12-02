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
public class Bound
{
	public final Float3 p1;
	public final Float3 p2;
	public Bound()
	{
		this.p1 = new Float3();
		this.p2 = new Float3();
	}
	public Bound( Float3 p1 , Float3 p2 )
	{
		this.p1 = p1;
		this.p2 = p2;
	}
	public boolean collide( Object area )
	{
		if( area instanceof Ray )
		{
			Float3 mp = ( Float3 )area;
			return mp.x > p1.x && mp.x < p2.x && mp.y > p1.y && mp.y < p2.y && mp.y > p1.y && mp.y < p2.y;
		}
		return false;
	}
	public Bound copy()
	{
		return new Bound( p1.copy() , p2.copy() );
	}
	public void copyIn( Bound b)
	{
		this.p1.copyIn( b.p1 );
		this.p2.copyIn( b.p2 );
	}
	public void unite( Bound b )
	{
		p1.copyIn( new Float3( Math.min( p1.x , b.p1.x ) , Math.min( p1.y , b.p1.y ) , Math.min( p1.z , b.p1.z ) ) );
		p2.copyIn( new Float3( Math.max( p2.x , b.p2.x ) , Math.max( p2.y , b.p2.y ) , Math.max( p1.z , b.p1.z ) ) );
	}
	public Bound getAligned()
	{
		Float3 np1 = new Float3( Math.min( p1.x , p2.x ) , Math.min( p1.y , p2.y ) , Math.min( p1.z , p1.z ) );
		Float3 np2 = new Float3( Math.max( p1.x , p2.x ) , Math.max( p1.y , p2.y ) , Math.max( p1.z , p1.z ) );
		return new Bound( np1 , np2 );
	}
	public void init( Float3 p , int dx )
	{
		p1.copyIn( new Float3( p.x - dx , p.y - dx , p.z - dx ) );
		p2.copyIn( new Float3( p.x + dx , p.y + dx , p.z + dx ) );
	}
	public void grow( Float3 p , int dx )
	{
		unite( new Bound( new Float3( p.x - dx , p.y - dx , p.z - dx ) , new Float3( p.x + dx , p.y + dx , p.z + dx ) ) );
	}
	public boolean contains( Float3 p )
	{
		return p.x > p1.x && p.x < p2.x && p.y > p1.y && p.y < p2.y && p.z > p1.z && p.z < p2.z;
	}
}
