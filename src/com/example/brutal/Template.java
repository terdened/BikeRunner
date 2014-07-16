package com.example.brutal;

import java.util.LinkedList;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Template {
	public LinkedList<Obstacle> objects;
	public int[] choosedObjects;
	
	public Template(int id, ResourcesManager resourcesManager, VertexBufferObjectManager pVertexBufferObjectManager)
	{
		objects=new LinkedList<Obstacle>();
		
		choosedObjects = new int[0];
		
		/*
		 *  0     1     2
		 * 
		 * zzz   zzz   zzz
		 * zzz   zzz   zzz
		 * tbb   btb   bbt
		 * 
		 *  3     4     5  
		 * zzz   zzz   zzz
		 * bzz   zbz   zzb
		 * tzz   ztz   zzt
		 * 
		 *  6     7     8
		 * zzz   zzz   zzz
		 * bbb   bbb   bbb
		 * tzz   ztz   zzt
		 * 
		 *  9     10    11
		 * bzz   zbz   zzb
		 * zzz   zzz   zzz
		 * zbb   bzb   bbz
		 */
		
		if(id==0)
		{			
			ObstacleTramp tramp1 = new ObstacleTramp(-380, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
			tramp1.initLine(0);
			ObstacleBus bus1 = new ObstacleBus(0, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.initLine(1);
			ObstacleBus bus2 = new ObstacleBus(380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus2.initLine(2);
			
			objects.add(tramp1);
			objects.add(bus1);
			objects.add(bus2);
		}
		else
		if(id==1)
		{
			ObstacleTramp tramp1 = new ObstacleTramp(0, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
			tramp1.initLine(1);
			ObstacleBus bus1 = new ObstacleBus(-380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.initLine(0);
			ObstacleBus bus2 = new ObstacleBus(380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus2.initLine(2);
			
			objects.add(tramp1);
			objects.add(bus1);
			objects.add(bus2);
		}
		else
		if(id==2)
		{
			ObstacleTramp tramp1 = new ObstacleTramp(380, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
			tramp1.initLine(2);
			ObstacleBus bus1 = new ObstacleBus(0, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.initLine(1);
			ObstacleBus bus2 = new ObstacleBus(-380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus2.initLine(0);
			
			objects.add(tramp1);
			objects.add(bus1);
			objects.add(bus2);
		}
		else
		if(id==3)
		{
			ObstacleTramp tramp1 = new ObstacleTramp(-380, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
			tramp1.initLine(0);
			ObstacleBus bus1 = new ObstacleBus(-380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.setDepth(1100);
			bus1.initLine(0);
			
			objects.add(tramp1);
			objects.add(bus1);
			
		}
		else
		if(id==4)
		{
			ObstacleTramp tramp1 = new ObstacleTramp(0, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
			tramp1.initLine(1);
			ObstacleBus bus1 = new ObstacleBus(0, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.setDepth(1100);
			bus1.initLine(1);
			
			objects.add(tramp1);
			objects.add(bus1);
		}
		else
		if(id==5)
		{
			ObstacleTramp tramp1 = new ObstacleTramp(380, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
			tramp1.initLine(2);
			ObstacleBus bus1 = new ObstacleBus(380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.setDepth(1100);
			bus1.initLine(2);
			
			objects.add(tramp1);
			objects.add(bus1);
		}
		else
		if(id==6)
		{
			ObstacleTramp tramp1 = new ObstacleTramp(-380, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
			tramp1.initLine(0);
			ObstacleBus bus1 = new ObstacleBus(-380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.setDepth(1100);
			bus1.initLine(0);
			ObstacleBus bus2 = new ObstacleBus(0, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus2.setDepth(1100);
			bus2.initLine(1);
			ObstacleBus bus3 = new ObstacleBus(380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus3.setDepth(1100);
			bus3.initLine(2);
			
			objects.add(tramp1);
			objects.add(bus1);
			objects.add(bus2);
			objects.add(bus3);
		}
		else
		if(id==7)
		{
			ObstacleTramp tramp1 = new ObstacleTramp(0, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
			tramp1.initLine(1);
			ObstacleBus bus1 = new ObstacleBus(-380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.setDepth(1100);
			bus1.initLine(0);
			ObstacleBus bus2 = new ObstacleBus(0, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus2.setDepth(1100);
			bus2.initLine(1);
			ObstacleBus bus3 = new ObstacleBus(380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus3.setDepth(1100);
			bus3.initLine(2);
			
			objects.add(tramp1);
			objects.add(bus1);
			objects.add(bus2);
			objects.add(bus3);
		}
		else
		if(id==8)
		{
			ObstacleTramp tramp1 = new ObstacleTramp(380, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
			tramp1.initLine(2);
			ObstacleBus bus1 = new ObstacleBus(-380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.setDepth(1100);
			bus1.initLine(0);
			ObstacleBus bus2 = new ObstacleBus(0, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus2.setDepth(1100);
			bus2.initLine(1);
			ObstacleBus bus3 = new ObstacleBus(380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus3.setDepth(1100);
			bus3.initLine(2);
			
			objects.add(tramp1);
			objects.add(bus1);
			objects.add(bus2);
			objects.add(bus3);
		}
		else
		if(id==9)
		{
			ObstacleBus bus1 = new ObstacleBus(380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.initLine(2);
			ObstacleBus bus2 = new ObstacleBus(0, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus2.initLine(1);
			ObstacleBus bus3 = new ObstacleBus(-380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus3.setDepth(1200);
			bus3.initLine(0);
			
			objects.add(bus1);
			objects.add(bus2);
			objects.add(bus3);
		}
		else
		if(id==10)
		{
			ObstacleBus bus1 = new ObstacleBus(380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.initLine(2);
			ObstacleBus bus2 = new ObstacleBus(-380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus2.initLine(0);
			ObstacleBus bus3 = new ObstacleBus(0, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus3.setDepth(1200);
			bus3.initLine(1);
			
			objects.add(bus1);
			objects.add(bus2);
			objects.add(bus3);
		}
		else
		if(id==11)
		{
			ObstacleBus bus1 = new ObstacleBus(-380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus1.initLine(0);
			ObstacleBus bus2 = new ObstacleBus(0, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus2.initLine(1);
			ObstacleBus bus3 = new ObstacleBus(380, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
			bus3.setDepth(1200);
			bus3.initLine(2);
			
			objects.add(bus1);
			objects.add(bus2);
			objects.add(bus3);
		}
	}
	
	
}
