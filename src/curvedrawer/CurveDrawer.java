/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
/**
 *
 * @author anton
 */
public class CurveDrawer
{
	public static void main( String[] args )
	{
		final JFrame jframe = new JFrame( "frame" );
		Camera cam = new Camera();
		cam.setLookAt( new Float3( 0.0f , -10.0f , 0.0f ) , new Float3( 0.0f , 0.0f , 0.0f ) , new Float3( 0.0f , 0.0f , 1.0f ) );
		List< List<Float3>> lines = new ArrayList();
		for( int i = 0; i <= 8; i++ )
		{
			List<Float3> line = new ArrayList();
			line.add( new Float3( -4.0f + i , 4.0f , 0.0f ).mul( 3.0f ) );
			line.add( new Float3( -4.0f + i , -4.0f , 0.0f ).mul( 3.0f ) );
			lines.add( line );
		}
		for( int i = 0; i <= 8; i++ )
		{
			List<Float3> line = new ArrayList();
			line.add( new Float3( 4.0f , -4.0f + i , 0.0f ).mul( 3.0f ) );
			line.add( new Float3( -4.0f , -4.0f + i , 0.0f ).mul( 3.0f ) );
			lines.add( line );
		}
		FuncCurve fcurve = new FuncCurve( ( x ) -> 10.0f * ( float ) Math.exp( Math.pow( x - 0.5f , 2 ) ) , 100 );
		final Screen screen = new Screen( 512 , 512 );
		jframe.add( new JComponent()
		{
			float angle = 0.0f;
			long lasttime = System.currentTimeMillis();
			@Override
			public void paint( Graphics g )
			{
				super.paint( g );
				Graphics2D g2d = ( Graphics2D ) g;
				RenderingHints rh = new RenderingHints(
						RenderingHints.KEY_ANTIALIASING ,
						RenderingHints.VALUE_ANTIALIAS_ON );
				g2d.setRenderingHints( rh );
				long time = System.currentTimeMillis();
				float dt = ( time - lasttime ) * 1.0e-3f;
				lasttime = time;
				angle += dt;
				cam.setLookAt( new Float3( ( float ) Math.sin( angle ) , ( float ) Math.cos( angle ) , 1.0f ).mul( 20.0f ) , new Float3( 0.0f , 0.0f , 0.0f ) , new Float3( 0.0f , 0.0f , 1.0f ) );
				Mat4 viewproj = cam.getMatrix();
				g2d.setColor( Color.red );
				screen.clear();
				for( List<Float3> l : lines )
				{
					Float4 lp = viewproj.mul( new Float4( l.get( 0 ) , 1.0f ) ).proj();
					for( Float3 p : l )
					{
						Float4 cp = viewproj.mul( new Float4( p , 1.0f ) ).proj();
						int sx = ( int ) ( ( lp.x * 0.5f + 0.5f ) * screen.getWidth() );
						int sy = ( int ) ( ( -lp.y * 0.5f + 0.5f ) * screen.getHeight() );
						int ex = ( int ) ( ( cp.x * 0.5f + 0.5f ) * screen.getWidth() );
						int ey = ( int ) ( ( -cp.y * 0.5f + 0.5f ) * screen.getHeight() );
						//System.err.println( sx + "," + sy + "->" + ex + "," + ey );
						//g2d.drawLine( sx , sy , ex , ey );
						LineDrawer.drawLine( screen , new Int2( sx , sy ) , new Int2( ex , ey ) , new Pixel( 0.0f , 0.0f , 1.0f , lp.z ) , new Pixel( 0.0f , 0.0f , 1.0f , p.z ) );
						lp = cp;
					}
				}
				Mat4 model = new Mat4();
				fcurve.draw( g2d , screen , viewproj , model );
				model.m[ 0 ] = 0.1f;
				model.m[ 5 ] = 0.1f;
				model.m[ 10 ] = 2.0f;
				fcurve.draw( g2d , screen , viewproj , model );
				model.m[ 0 ] = 1.0f;
				model.m[ 5 ] = 1.0f;
				model.m[ 4 ] = 1.0f;
				model.m[ 10 ] = 1.0f;
				model.m[ 7 ] = 20.0f;
				fcurve.draw( g2d , screen , viewproj , model );
				for( int i = 0; i < screen.getHeight(); i++ )
				{
					for( int j = 0; j < screen.getWidth(); j++ )
					{
						float d = screen.getDepth(i , j );
						if( d > 0.9f )
						{
							continue;
						}
						g2d.setColor( screen.get( i , j ) );
						g2d.drawRect( i , j , 0 , 0 );
					}
				}
				//g2d.setColor( Color.BLACK );
				//viewproj.print();
			}
		} );
		jframe.setSize( 512 , 512 );
		jframe.setVisible( true );
		jframe.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		while( jframe.isVisible() )
		{
			jframe.repaint();
		}
	}
}
