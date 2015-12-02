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
public class Ray
{
	public final Float3 p;
	public final Float3 v;
	public Ray( Float3 p , Float3 v )
	{
		this.p = p;
		this.v = v;
	}
}
