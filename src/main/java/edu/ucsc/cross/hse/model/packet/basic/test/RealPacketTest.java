
package edu.ucsc.cross.hse.model.packet.basic.test;

import edu.ucsc.cross.hse.model.data.objects.RealData;
import edu.ucsc.cross.hse.model.data.objects.SimulatedData;

public class RealPacketTest
{

	public static void main(String args[])
	{
		RealData pack = new RealData();
		double dub = 2044010000.0;
		double dub2 = 2440013000.0;
		double dub3 = 2004897022.0;

		pack.addObject(dub, dub2, dub3);//, s);
		System.out.println(pack.getSize());
		System.out.println(pack.getDataSet(Double.class));
		SimulatedData sim = new SimulatedData(300.0);
		System.out.println(sim.getSize());

	}

	public static enum Pack
	{
		ONE,
		TWO;
	}
}
