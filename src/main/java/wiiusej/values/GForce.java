/**
 * This file is part of WiiuseJ.
 *
 *  WiiuseJ is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  WiiuseJ is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with WiiuseJ.  If not, see <http://www.gnu.org/licenses/>.
 */
package wiiusej.values;

/**
 * Represents gravity force on each axis.
 * 
 * @author guiguito
 */
public class GForce {

	private float x;
	private float y;
	private float z;

	/**
	 * Default constructor;
	 */
	public GForce() {
		x = 0;
		y = 0;
		z = 0;
	}

	/**
	 * Constructor with gravity force on each axis.
	 * 
	 * @param xx
	 *            x value
	 * @param yy
	 *            x value
	 * @param zz
	 *            x value
	 */
	public GForce(float xx, float yy, float zz) {
		x = xx;
		y = yy;
		z = zz;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @return the z
	 */
	public float getZ() {
		return z;
	}

	@Override
	public String toString() {
		return "Gravity force : (" + x + ", " + y + "," + z + ")";
	}
}
