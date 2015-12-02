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
public class Camera
{
	private final Float3 pos = new Float3();
	private final Float3 up = new Float3();
	private final Float3 left = new Float3();
	private final Float3 look = new Float3();
	private float itanx = 1.0f;
	private float itany = 1.0f;
	private float farz = 1000.0f;
	private float nearz = 0.01f;
	public void setPos( Float3 p )
	{
		pos.copyIn( p );
	}
	public Float3 getPos()
	{
		return pos.copy();
	}
	public Camera setLookAt( Float3 p , Float3 l , Float3 up )
	{
		this.pos.copyIn( p );
		this.look.copyIn( p.sub( l ).norm() );
		this.left.copyIn( look.vecx( up ).norm() );
		this.up.copyIn( left.vecx( look ).norm() );
		return this;
	}
	public Mat4 getMatrix()
	{
		return proj().mul( view() );
	}
	private Mat4 view()
	{
		return new Mat4(
				new Float4( left , -left.dot( pos ) ) ,
				new Float4( up , -up.dot( pos ) ) ,
				new Float4( look , -look.dot( pos ) ) ,
				new Float4( 0.0f , 0.0f , 0.0f , 1.0f ) );
	}
	private Mat4 proj()
	{
		return new Mat4(
				new Float4( itanx , 0.0f , 0.0f , 0.0f ) ,
				new Float4( 0.0f , itany , 0.0f , 0.0f ) ,
				new Float4( 0.0f , 0.0f , 2.0f / ( farz - nearz ) ,  0.0f ) ,
				new Float4( 0.0f , 0.0f , -( farz + nearz ) / ( farz - nearz ) , 1.0f ) );
	}
}
