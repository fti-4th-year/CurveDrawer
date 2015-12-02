/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curvedrawer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
/**
 *
 * @author anton
 */
public class DataBuffer
{
	private ByteBuffer data = ByteBuffer.wrap( new byte[ 256 ] );
	private int size = 256;
	private int filled = 0;
	private final int step = 256;
	private void resizeIfNeed( int add )
	{
		if( filled + add > size )
		{
			byte[] nb = new byte[ size + step ];
			System.arraycopy( data.array() , 0 , nb , 0 , size );
			size = size + step;
			data = ByteBuffer.wrap( nb );
		}
		filled += add;
	}
	public DataBuffer clear()
	{
		data.clear();
		filled = 0;
		return this;
	}
	public DataBuffer add( Float3 p )
	{
		resizeIfNeed( 12 );
		ByteBuffer b = ByteBuffer.wrap( new byte[ 12 ] );
		b.putFloat( p.x );
		b.putFloat( p.y );
		b.putFloat( p.z );
		return this;
	}
	public Float3 getFloat3( int i )
	{
		return null;
	}
}
