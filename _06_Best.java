// Created by Or Kadrawi

public class _06_Best {
	
	int startIndex;
	int endIndex;
	int sum;
	
	public void Best(int[] a)
	{
        int max = a[0], 
        temp_max = 0,start = 0, 
        end = 0, s = 0; 
  
        for (int i = 0; i < a.length; i++)  
        { 
        	temp_max += a[i]; 
  
            if (max < temp_max)  
            { 
            	max = temp_max; 
                start = s; 
                end = i; 
            } 
  
            if (temp_max < 0)  
            { 
            	temp_max = 0; 
                s = i + 1; 
            } 
        } 
        sum =  max; 
        startIndex =  start; 
        endIndex = end;
	}

}