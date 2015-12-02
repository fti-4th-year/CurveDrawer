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
public class Pixel
{
	public float r, g, b, d;
	public Pixel( float r , float g , float b , float a )
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.d = a;
	}
	public void copyIn( Pixel color )
	{
		this.r = color.r;
		this.g = color.g;
		this.b = color.b;
		this.d = color.d;
	}
	public Pixel copy()
	{
		return new Pixel( r , g , b , d );
	}
	public Pixel lerp( Pixel c , float l )
	{
		float il = 1.0f - l;
		return new Pixel( r * il + c.r * l , g * il + c.g * l , b * il + c.b * l , d * il + c.d * l );
	}
}
