/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
import java.util.Vector;
import javafx.util.Pair;
/**
 *
 * @author anton
 */
public class Pipeline
{
	public static interface VertexTransform
	{
		void transform( float[] in , float[] out );
	}
	public static interface Rasterizer
	{
		Pair< Int2 , float[]>[] raster( float[] in , int width , int height );
	}
	public static interface PixelTransform
	{
		Pixel transform( Float2 tx , float[] in );
	}
	private final VertexTransform vtrans;
	private final PixelTransform ptrans;
	private final Rasterizer raster;
	public Pipeline( VertexTransform vtrans , Rasterizer raster , PixelTransform ptrans )
	{
		this.vtrans = vtrans;
		this.ptrans = ptrans;
		this.raster = raster;
	}
	public void draw( Screen out , float[] vertex_data , int vertex_count , int fpervertex )
	{
		float[] vertex_to_raster = new float[ vertex_data.length ];
		float[] tmp_in = new float[ fpervertex ];
		float[] tmp_out = new float[ fpervertex ];
		for( int i = 0; i < vertex_count; i++ )
		{
			System.arraycopy( vertex_data , i * fpervertex , tmp_in , 0 , fpervertex );
			vtrans.transform( tmp_in , tmp_out );
			System.arraycopy( tmp_out , 0 , vertex_to_raster , i , fpervertex );
		}
		Vector< Float2> pixels = new Vector();
		for( int i = 0; i < vertex_count; i++ )
		{
			System.arraycopy( vertex_to_raster , i * fpervertex , tmp_in , 0 , fpervertex );
			Pair< Int2 , float[]>[] pt = raster.raster( tmp_in , out.getWidth() , out.getHeight() );
			if( pt == null )
			{
				continue;
			}
			for( int j = 0; j < pt.length; j++ )
			{
				Float2 tx = new Float2( ( float ) pt[ j ].getKey().x * 2.0f - 1.0f , ( float ) pt[ j ].getKey().y * 2.0f - 1.0f );
				out.set( ptrans.transform( tx , pt[ j ].getValue() ) , pt[ j ].getKey().x , pt[ j ].getKey().y );
			}
		}
	}
}
