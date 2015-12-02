/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author anton
 */
public class FuncCurve
{
	private final List< Float> points = new ArrayList();
	public static interface Func
	{
		float f( float r );
	}
	private final Func func;
	public FuncCurve( Func p , int N )
	{
		float dt = 1.0f / ( N - 1 );
		for( int i = 0; i < N; i++ )
		{
			points.add( p.f( dt * i ) );
		}
		func = p;
	}
	private Pixel genPixel( float z , float angle , float depth )
	{
		if( angle < 0.0f )
		{
			angle += 2.0 * Math.PI;
		}
		if( angle > 2.0 * Math.PI )
		{
			angle -= 2.0 * Math.PI;
		}
		Color c = Color.getHSBColor( angle / ( float ) Math.PI * 0.4999f , 1.0f , 2.0f );
		return new Pixel( c.getRed() / 255.0f , c.getGreen() / 255.0f , c.getBlue() / 255.0f , depth );
	}
	public void draw( Graphics2D g , Screen screen , Mat4 viewproj , Mat4 model )
	{
		List< Float3> vpoints = new ArrayList();
		float z = 0.0f;
		final float zmax = 10.0f;
		float dz = zmax / ( points.size() - 1 );
		for( float p : points )
		{
			vpoints.add( new Float3( p , 0.0f , z ) );
			z += dz;
		}
		for( float angle = 0.0f; angle <= 2.0f * Math.PI; angle += Math.PI / 4 )
		{
			Mat3 rot = new Mat3( angle );
			Float3 lp3 = vpoints.get( 0 );
			Float4 lp = viewproj.mul( model.mul( new Float4( rot.mul( vpoints.get( 0 ) ) , 1.0f ) ) ).proj();
			for( Float3 p : vpoints )
			{
				Float4 cp = viewproj.mul( model.mul( new Float4( rot.mul( p ) , 1.0f ) ) ).proj();
				int sx = ( int ) ( ( lp.x * 0.5f + 0.5f ) * screen.getWidth() );
				int sy = ( int ) ( ( -lp.y * 0.5f + 0.5f ) * screen.getHeight() );
				int ex = ( int ) ( ( cp.x * 0.5f + 0.5f ) * screen.getWidth() );
				int ey = ( int ) ( ( -cp.y * 0.5f + 0.5f ) * screen.getHeight() );
				LineDrawer.drawLine( screen , new Int2( sx , sy ) , new Int2( ex , ey ) , new Pixel( 0.0f , 0.0f , 0.0f , lp.z ) , new Pixel( 0.0f , 0.0f , 0.0f , cp.z ) );
				//g.drawLine( sx , sy , ex , ey );
				lp = cp;
				lp3 = p;
			}
		}
		Mat3 rot = new Mat3( ( float ) Math.PI / 16 );
		for( int i = 0; i <= vpoints.size(); i += 10 )
		{
			if( i == vpoints.size() )
			{
				i = i - 1;
			}
			Float3 lp = vpoints.get( i );
			Float4 lpj = viewproj.mul( model.mul( new Float4( lp , 1.0f ) ) ).proj();
			for( float angle = 0.0f; angle <= 2.0f * Math.PI; angle += Math.PI / 16 )
			{
				Float3 np = rot.mul( lp );
				Float4 p = viewproj.mul( model.mul( new Float4( np , 1.0f ) ) ).proj();
				int sx = ( int ) ( ( lpj.x * 0.5f + 0.5f ) * screen.getWidth() );
				int sy = ( int ) ( ( -lpj.y * 0.5f + 0.5f ) * screen.getHeight() );
				int ex = ( int ) ( ( p.x * 0.5f + 0.5f ) * screen.getWidth() );
				int ey = ( int ) ( ( -p.y * 0.5f + 0.5f ) * screen.getHeight() );
				LineDrawer.drawLine( screen , new Int2( sx , sy ) , new Int2( ex , ey ) , new Pixel( 0.0f , 0.0f , 0.0f , lpj.z ) , new Pixel( 0.0f , 0.0f , 0.0f , p.z ) );
				//g.drawLine( sx , sy , ex , ey );
				lp = np;
				lpj = p;
			}
			if( i == vpoints.size() - 1 )
			{
				break;
			}
		}
	}
}
