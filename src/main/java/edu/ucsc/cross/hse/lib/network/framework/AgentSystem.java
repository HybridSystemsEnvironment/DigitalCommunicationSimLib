package edu.ucsc.cross.hse.lib.network.framework;

import java.util.ArrayList;

import edu.ucsc.cross.hse.core.modeling.HybridSystem;
import edu.ucsc.cross.hse.core.variable.RandomVariable;
import edu.ucsc.cross.hse.lib.network.Connection;
import edu.ucsc.cross.hse.lib.network.Network;
import edu.ucsc.cross.hse.lib.network.Node;
import edu.ucsc.cross.hse.model.data.objects.SimulatedData;
import edu.ucsc.cross.hse.model.data.packet.BasicPacket;
import edu.ucsc.cross.hse.model.data.packet.Packet;
import edu.ucsc.cross.hse.model.data.packet.header.MinimalHeader;
import edu.ucsc.cross.hse.model.storage.StorageInterface;

public class AgentSystem extends HybridSystem<SpammerState>
{

	public Node localNode;
	public SimulatedAgentParameters params;
	public ArrayList<Packet> packetsReceived = new ArrayList<Packet>();
	public StorageInterface storage;

	public AgentSystem(SpammerState state, SimulatedAgentParameters params, Node localNode, StorageInterface storage)
	{
		super(state, params);
		this.localNode = localNode;
		this.params = params;
		this.storage = storage;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean C(SpammerState arg0)
	{

		return true;
	}

	@Override
	public boolean D(SpammerState arg0)
	{
		// TODO Auto-generated method stub
		return arg0.timeToNextSend <= 0.0 || localNode.getReceivingBuffer().size() > 0;

	}

	@Override
	public void F(SpammerState arg0, SpammerState arg1)
	{
		arg1.timeToNextSend = -1.0;
	}

	@Override
	public void G(SpammerState arg0, SpammerState arg1)
	{
		if (arg0.timeToNextSend <= 0.0)
		{
			Integer edgesCount = Network.getDirectionalConnections(localNode, false).size();

			arg1.timeToNextSend = RandomVariable.generate(params.minSendTime, params.maxSendTime);
			Integer target = (int) Math.floor(edgesCount * Math.random());
			Connection conn = Network.getDirectionalConnections(localNode, false).get(target);
			MinimalHeader h = new MinimalHeader("Hi", localNode, conn.getTarget());
			SimulatedData sim = new SimulatedData(RandomVariable.generate(params.minSendSize, params.maxSendSize));
			localNode.getTransmittingBuffer().add(new BasicPacket(h, sim));
			arg1.sizeSent += h.getHeaderSize() + sim.getSize();
			//System.out.println(arg1.sizeSent);
		}
		if (localNode.getReceivingBuffer().size() > 0)
		{
			packetsReceived.addAll(localNode.getReceivingBuffer());
			localNode.getReceivingBuffer().clear();
			double size = arg0.sizeReceived;
			for (Packet p : packetsReceived)
			{
				size += p.getTotalSize();
			}
			arg1.sizeReceived = size;
			//System.out.println(arg1.sizeReceived);

		}
		for (Packet packet : packetsReceived)
		{
			storage.write(packet.getPayload());

		}
		packetsReceived.clear();
	}
}
