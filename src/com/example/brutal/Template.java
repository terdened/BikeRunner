package com.example.brutal;

import java.util.LinkedList;

public class Template {
	public LinkedList<TemplateObject> objects;
	public int[] choosedObjects;
	
	public Template(int id, ResourcesManager resourcesManager)
	{
		objects=new LinkedList<TemplateObject>();
		
		choosedObjects = new int[0];
		
		/*
		 * 0
		 * zzz
		 * zzz
		 * tbb
		 */
		
		/*
		 * 1
		 * zzz
		 * zzz
		 * btb
		 */
		
		/*
		 * 2
		 * zzz
		 * zzz
		 * bbt
		 */
		
		/*
		 * 3
		 * zzz
		 * bzz
		 * tzz
		 */
		
		/*
		 * 4
		 * zzz
		 * zbz
		 * ztz
		 */
		
		/*
		 * 5
		 * zzz
		 * zzb
		 * zzt
		 */
		
		/*
		 * 6
		 * zzz
		 * bbb
		 * tzz
		 */
		
		/*
		 * 7
		 * zzz
		 * bbb
		 * ztz
		 */
		
		/*
		 * 8
		 * zzz
		 * bbb
		 * zzt
		 */
		
		if(id==0)
		{
			choosedObjects = new int[3];
			choosedObjects[0] = 0;
			choosedObjects[1] = 4;
			choosedObjects[2] = 5;
		}
		else
		if(id==1)
		{
			choosedObjects = new int[3];
			choosedObjects[0] = 1;
			choosedObjects[1] = 3;
			choosedObjects[2] = 5;
		}
		else
		if(id==2)
		{
			choosedObjects = new int[3];
			choosedObjects[0] = 2;
			choosedObjects[1] = 4;
			choosedObjects[2] = 3;
		}
		else
		if(id==3)
		{
			choosedObjects = new int[2];
			choosedObjects[0] = 0;
			choosedObjects[1] = 6;
			
		}
		else
		if(id==4)
		{
			choosedObjects = new int[2];
			choosedObjects[0] = 1;
			choosedObjects[1] = 7;
		}
		else
		if(id==5)
		{
			choosedObjects = new int[2];
			choosedObjects[0] = 2;
			choosedObjects[1] = 8;
		}
		else
		if(id==6)
		{
			choosedObjects = new int[4];
			choosedObjects[0] = 0;
			choosedObjects[1] = 6;
			choosedObjects[2] = 7;
			choosedObjects[3] = 8;
		}
		else
		if(id==7)
		{
			choosedObjects = new int[4];
			choosedObjects[0] = 1;
			choosedObjects[1] = 6;
			choosedObjects[2] = 7;
			choosedObjects[3] = 8;
		}
		else
		if(id==8)
		{
			choosedObjects = new int[4];
			choosedObjects[0] = 2;
			choosedObjects[1] = 6;
			choosedObjects[2] = 7;
			choosedObjects[3] = 8;
		}
		else
		if(id==9)
		{
			choosedObjects = new int[3];
			choosedObjects[0] = 6;
			choosedObjects[1] = 4;
			choosedObjects[2] = 5;
		}
		else
		if(id==10)
		{
			choosedObjects = new int[3];
			choosedObjects[0] = 3;
			choosedObjects[1] = 7;
			choosedObjects[2] = 5;
		}
		else
		if(id==11)
		{
			choosedObjects = new int[3];
			choosedObjects[0] = 3;
			choosedObjects[1] = 4;
			choosedObjects[2] = 8;
		}
		else
		if(id==12)
		{
			choosedObjects = new int[2];
			choosedObjects[0] = 3;
			choosedObjects[1] = 11;
		}
		else
		if(id==13)
		{
			choosedObjects = new int[2];
			choosedObjects[0] = 5;
			choosedObjects[1] = 9;
		}
		else
		if(id==14)
		{
			choosedObjects = new int[2];
			choosedObjects[0] = 3;
			choosedObjects[1] = 10;
		}else
		if(id==15)
		{
			choosedObjects = new int[2];
			choosedObjects[0] = 10;
			choosedObjects[1] = 11;
		}
		else
		if(id==16)
		{
			choosedObjects = new int[2];
			choosedObjects[0] = 9;
			choosedObjects[1] = 11;
		}
		else
		if(id==17)
		{
			choosedObjects = new int[2];
			choosedObjects[0] = 9;
			choosedObjects[1] = 10;
		}
		
		
		
		for(int i=0;i<choosedObjects.length;i++)
		{
			if(choosedObjects[i]==0)
			{
				/*
				 * zzz
				 * zzz
				 * tzz
				 */
				TemplateObject obj1 = new TemplateObject();
				obj1.type="tramp";
				obj1.x = 380;
				obj1.y = 128;
				obj1.region = resourcesManager.tramp_a_region;
				obj1.line = 0;
				obj1.height = 200;
				obj1.realY = 128;
				obj1.size = 1;
				obj1.length = 250;
				obj1.z = 600;
				
				objects.add(obj1);
			}
			else
			if(choosedObjects[i]==1)
			{
				/*
				 * zzz
				 * zzz
				 * ztz
				 */
				TemplateObject obj1 = new TemplateObject();
				obj1.type="tramp";
				obj1.x = 380;
				obj1.y = 128;
				obj1.region = resourcesManager.tramp_a_region;
				obj1.line = 1;
				obj1.height = 200;
				obj1.realY = 128;
				obj1.size = 1;
				obj1.length = 250;
				obj1.z = 600;
				
				objects.add(obj1);
			}
			else
			if(choosedObjects[i]==2)
			{
				/*
				 * zzz
				 * zzz
				 * zzt
				 */
				TemplateObject obj1 = new TemplateObject();
				obj1.type="tramp";
				obj1.x = 380;
				obj1.y = 128;
				obj1.region = resourcesManager.tramp_a_region;
				obj1.line = 2;
				obj1.height = 200;
				obj1.realY = 128;
				obj1.size = 1;
				obj1.length = 250;
				obj1.z = 600;
				
				objects.add(obj1);
			}
			else
			if(choosedObjects[i]==3)
			{
				/*
				 * zzz
				 * zzz
				 * bzz
				 */
				TemplateObject obj1 = new TemplateObject();
				obj1.type="box";
				obj1.x = 380;
				obj1.y = 128;
				obj1.region = resourcesManager.bus_a_region;
				obj1.line = 0;
				obj1.height = 200;
				obj1.realY = 128;
				obj1.size = 1;
				obj1.length = 250;
				obj1.z = 600;
				
				objects.add(obj1);
			}
			else
			if(choosedObjects[i]==4)
			{
				/*
				 * zzz
				 * zzz
				 * zbz
				 */
				TemplateObject obj1 = new TemplateObject();
				obj1.type="box";
				obj1.x = 380;
				obj1.y = 128;
				obj1.region = resourcesManager.bus_a_region;
				obj1.line = 1;
				obj1.height = 200;
				obj1.realY = 128;
				obj1.size = 1;
				obj1.length = 250;
				obj1.z = 600;
				
				objects.add(obj1);
			}
			else
			if(choosedObjects[i]==5)
			{
				/*
				 * zzz
				 * zzz
				 * zzb
				 */
				TemplateObject obj1 = new TemplateObject();
				obj1.type="box";
				obj1.x = 380;
				obj1.y = 128;
				obj1.region = resourcesManager.bus_a_region;
				obj1.line = 2;
				obj1.height = 200;
				obj1.realY = 128;
				obj1.size = 1;
				obj1.length = 250;
				obj1.z = 600;
				
				objects.add(obj1);
			}
			else
			if(choosedObjects[i]==6)
			{
				/*
				 * zzz
				 * bzz
				 * zzz
				 */
				TemplateObject obj1 = new TemplateObject();
				obj1.type="box";
				obj1.x = 380;
				obj1.y = 128;
				obj1.region = resourcesManager.bus_a_region;
				obj1.line = 0;
				obj1.height = 200;
				obj1.realY = 128;
				obj1.size = 1;
				obj1.length = 250;
				obj1.z = 1100;
				
				objects.add(obj1);
			}
			else
			if(choosedObjects[i]==7)
			{
				/*
				 * zzz
				 * zbz
				 * zzz
				 */
				TemplateObject obj2 = new TemplateObject();
				obj2.type="box";
				obj2.x = 380;
				obj2.y = 128;
				obj2.region = resourcesManager.bus_a_region;
				obj2.line = 1;
				obj2.height = 200;
				obj2.realY = 128;
				obj2.size = 1;
				obj2.length = 250;
				obj2.z = 1100;
				
				objects.add(obj2);
			}
			else
			if(choosedObjects[i]==8)
			{
				/*
				 * zzz
				 * zzb
				 * zzz
				 */
				TemplateObject obj2 = new TemplateObject();
				obj2.type="box";
				obj2.x = 380;
				obj2.y = 128;
				obj2.region = resourcesManager.bus_a_region;
				obj2.line = 2;
				obj2.height = 200;
				obj2.realY = 128;
				obj2.size = 1;
				obj2.length = 250;
				obj2.z = 1100;
				
				objects.add(obj2);
			}else
			if(choosedObjects[i]==9)
			{
				/*
				 * zzz
				 * zzz
				 * fzz
				 */
				TemplateObject obj2 = new TemplateObject();
				obj2.type="box";
				obj2.x = 380;
				obj2.y = 128;
				obj2.region = resourcesManager.fence_region;
				obj2.line = 0;
				obj2.height = 100;
				obj2.realY = 128;
				obj2.size = 1;
				obj2.length = 250;
				obj2.z = 600;
				
				objects.add(obj2);
			}else
			if(choosedObjects[i]==10)
			{
				/*
				 * zzz
				 * zzz
				 * zfz
				 */
				TemplateObject obj2 = new TemplateObject();
				obj2.type="box";
				obj2.x = 380;
				obj2.y = 128;
				obj2.region = resourcesManager.fence_region;
				obj2.line = 1;
				obj2.height = 100;
				obj2.realY = 128;
				obj2.size = 1;
				obj2.length = 250;
				obj2.z = 600;
				
				objects.add(obj2);
			}else
			if(choosedObjects[i]==11)
			{
				/*
				 * zzz
				 * zzz
				 * zzf
				 */
				TemplateObject obj2 = new TemplateObject();
				obj2.type="box";
				obj2.x = 380;
				obj2.y = 128;
				obj2.region = resourcesManager.fence_region;
				obj2.line = 2;
				obj2.height = 100;
				obj2.realY = 128;
				obj2.size = 1;
				obj2.length = 250;
				obj2.z = 600;
				
				objects.add(obj2);
			}
			
		}
	}
	
	
}
