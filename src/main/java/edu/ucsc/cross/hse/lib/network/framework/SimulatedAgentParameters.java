package edu.ucsc.cross.hse.lib.network.framework;

public class SimulatedAgentParameters
{

	public double minSendTime;
	public double maxSendTime;
	public double minSendSize;
	public double maxSendSize;

	public SimulatedAgentParameters(double min_send, double max_send, double min_size, double max_size)
	{
		this.minSendTime = min_send;
		this.maxSendTime = max_send;
		this.minSendSize = min_size;
		this.maxSendSize = max_size;
	}

}
