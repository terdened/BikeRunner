package com.example.brutal;

import java.util.LinkedList;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Template {
	public LinkedList<Obstacle> objects;
	public int[] choosedObjects;
	
	public void TemplateGenerator(String[] template, ResourcesManager resourcesManager, VertexBufferObjectManager pVertexBufferObjectManager)
	{
		String[] deeps = template;
		
		for(int i=0;i<deeps.length;i++)
		{
			for(int j=0;j<deeps[i].length();j++)
			{
				int xDislocation=0;
				
				if(j==0)
					xDislocation=-380;
				else
				if(j==2)
					xDislocation=380;
				
				String symbol=deeps[i].substring(j, j+1);
				
				if(symbol.equals("b"))
				{
					ObstacleBus bus = new ObstacleBus(xDislocation, 128, resourcesManager.bus_region, pVertexBufferObjectManager);
					bus.initLine(j);
					objects.add(bus);
				}
				else
				if(symbol.equals("t"))
				{
					ObstacleTramp tramp = new ObstacleTramp(xDislocation, 128, resourcesManager.tramp_a_region, pVertexBufferObjectManager);
					tramp.initLine(j);
					objects.add(tramp);
				}
				else
				if(symbol.equals("f"))
				{
					ObstacleFency fence = new ObstacleFency(xDislocation, 128, resourcesManager.fence_region, pVertexBufferObjectManager);
					fence.initLine(j);
					objects.add(fence);
				}
				else
				if(symbol.equals("p"))
				{
					ObstaclePickup pickup = new ObstaclePickup(xDislocation, 128, resourcesManager.pickup_region, pVertexBufferObjectManager);
					pickup.initLine(j);
					objects.add(pickup);
				}
				else
				if(symbol.equals("h"))
				{
					ObstacleBuilding building = new ObstacleBuilding(xDislocation, 128, resourcesManager.building_region, pVertexBufferObjectManager);
					building.initLine(j);
					objects.add(building);
				}
				
				if(!symbol.equals("z"))
					objects.getLast().setDepth(1100-250*i);
			}
		}
	}
	
	public Template(int id, ResourcesManager resourcesManager, VertexBufferObjectManager pVertexBufferObjectManager)
	{
		objects=new LinkedList<Obstacle>();
		
		choosedObjects = new int[0];
		String[] template;
		template = new String[3];
		
		switch (id) 
		{
			case 0: 
				template[0]="zzz";
				template[1]="zzz";
				template[2]="tbb";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
				break;
	        case 1: 
				template[0]="zzz";
				template[1]="zzz";
				template[2]="btb";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
                break;
	        case 2:  
		    	template[0]="zzz";
				template[1]="zzz";
				template[2]="bbt";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 3:  
	        	template[0]="zzz";
				template[1]="bzz";
				template[2]="tzz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 4:  
	        	template[0]="zzz";
				template[1]="zbz";
				template[2]="ztz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 5:  
	        	template[0]="zzz";
				template[1]="zzb";
				template[2]="zzt";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 6:  
	        	template[0]="zzz";
				template[1]="bbb";
				template[2]="tzz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 7:  
	        	template[0]="zzz";
				template[1]="bbb";
				template[2]="ztz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 8:  
	        	template[0]="zzz";
				template[1]="bbb";
				template[2]="zzt";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 9:  
	        	template[0]="bzz";
				template[1]="zzz";
				template[2]="zbb";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 10: 
	        	template[0]="zbz";
				template[1]="zzz";
				template[2]="bzb";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 11: 
	        	template[0]="zzb";
				template[1]="zzz";
				template[2]="bbz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 12: 
	        	template[0]="zzz";
				template[1]="zzz";
				template[2]="hff";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 13: 
	        	template[0]="zzz";
				template[1]="zzz";
				template[2]="ffh";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 14: 
	        	template[0]="zzz";
				template[1]="zzz";
				template[2]="pfz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 15: 
	        	template[0]="zzz";
				template[1]="zzz";
				template[2]="zpf";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 16: 
	        	template[0]="zzz";
				template[1]="zzz";
				template[2]="pzf";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 17: 
	        	template[0]="zzz";
				template[1]="bzz";
				template[2]="tff";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 18: 
	        	template[0]="zzz";
				template[1]="zbz";
				template[2]="ftf";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 19: 
	        	template[0]="zzz";
				template[1]="zzb";
				template[2]="fft";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 20: 
	        	template[0]="zzz";
				template[1]="zzb";
				template[2]="fzt";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 21: 
	        	template[0]="zzz";
				template[1]="zbz";
				template[2]="ftz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 22: 
	        	template[0]="zzz";
				template[1]="bzz";
				template[2]="tzf";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 23: 
	        	template[0]="zpz";
				template[1]="zzz";
				template[2]="zzh";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 24: 
	        	template[0]="zzz";
				template[1]="zzz";
				template[2]="hzh";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 25: 
	        	template[0]="zpz";
				template[1]="zzz";
				template[2]="hzz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 26: 
	        	template[0]="zpz";
				template[1]="zzz";
				template[2]="hzh";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 27: 
	        	template[0]="hzh";
				template[1]="zzz";
				template[2]="zpz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 28:
	        	template=new String[1];
	        	template[0]="fzz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 29:
	        	template=new String[1];
	        	template[0]="zzf";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 30:
	        	template=new String[1];
	        	template[0]="bzz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 31:
	        	template=new String[1];
	        	template[0]="zbz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 32:
	        	template=new String[1];
	        	template[0]="zzb";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 33:
	        	template=new String[1];
	        	template[0]="zzp";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 34:
	        	template=new String[1];
	        	template[0]="zpz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        case 35:
	        	template=new String[1];
	        	template[0]="pzz";
				TemplateGenerator(template,resourcesManager,pVertexBufferObjectManager);
		        break;
	        default:
                break;
		}		
	}
	
	
}
