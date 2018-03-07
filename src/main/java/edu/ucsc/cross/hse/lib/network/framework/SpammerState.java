package edu.ucsc.cross.hse.lib.network.framework;

import edu.ucsc.cross.hse.core.modeling.DataStructure;

public class SpammerState extends DataStructure
{

	public double timeToNextSend;
	public double sizeSent;
	public double sizeReceived;

	public SpammerState(double timeToNextSend)
	{
		super();
		this.timeToNextSend = timeToNextSend;
		this.sizeSent = 0.0;
		sizeReceived = 0.0;
	}

}
