import java.lang.reflect.Field;
public class Reflect{
	public Reflect (){

	}
	public double somme(String attribut,Object[] asom) throws Exception{
		Class c=asom[0].getClass();
		Field f=c.getDeclaredField(attribut);
		f.setAccessible(true);
		int taille=asom.length;
		Object[] obj=new Object[taille];
		double[] val=new double[taille];
		double result=0;
		int i=0;
		while(i<taille){
			obj[i]=f.get(asom[i]);
			val[i]=(double)obj[i];
			result=result+val[i];
		i++;
		}
		return result;
		
	}
	public double min(String attribut,Object[] asom) throws Exception{
		Class c=asom[0].getClass();
		Field f=c.getDeclaredField(attribut);
		f.setAccessible(true);
		int taille=asom.length;
		Object[] obj=new Object[taille];
		double[] val=new double[taille];
		double result=0;
		int i=0;
		while(i<taille-1){
			obj[i]=f.get(asom[i]);
			val[i]=(double)obj[i];
			val[i+1]=(double)obj[i+1];
			if(val[i]<val[i+1]){
				result=val[i];
			}	
			else if(val[i]<val[i+1]){
				result=val[i+1];
			}
			
		i++;
		}
		return result;
		
	}
	
	public double max(String attribut,Object[] asom) throws Exception{
		Class c=asom[0].getClass();
		Field f=c.getDeclaredField(attribut);
		f.setAccessible(true);
		int taille=asom.length;
		Object[] obj=new Object[taille];
		double[] val=new double[taille];
		double result=0;
		int i=0;
		while(i<taille-1){
			obj[i]=f.get(asom[i]);
			val[i]=(double)obj[i];
			val[i+1]=(double)obj[i+1];
			if(val[i]<val[i+1]){
				result=val[i+1];
			}	
			else if(val[i]<val[i+1]){
				result=val[i];
			}
			
		i++;
		}
		return result;
		
	}
	
	public double moyenne(String attribut,Object[] asom) throws Exception{
		Class c=asom[0].getClass();
		Field f=c.getDeclaredField(attribut);
		f.setAccessible(true);
		int taille=asom.length;
		Object[] obj=new Object[taille];
		double[] val=new double[taille];
		double result=0;
		int i=0;
		while(i<taille){
			obj[i]=f.get(asom[i]);
			val[i]=(double)obj[i];
			result=result+val[i];
		i++;
		}
		double moyenne=result/taille;
		return moyenne;
		
	}

	public double[] triageDesc(Object[] atrier, String attribut) throws Exception{
		Class classe=atrier[0].getClass();
		Field field = classe.getDeclaredField(attribut);
		field.setAccessible(true);
		int taille=atrier.length;
		Object[] obj=new Object[taille];
		double[] result=new double[taille];
		
		for(int i = 0; i<taille ; i++){
            obj[i]=field.get(atrier[i]);
			result[i]=(double)obj[i];
        }

		for(int i = 0; i<taille ; i++){
            for(int j = 0; j <taille-j ; j++){
                if(result[j]< result[j+1]){
                    double rep = result[j];
                    result[j]=result[j+1];
                    result[j+1]=rep;
                }
            }
        }
        return result;
	}

	public double[] triageAsc(Object[] atrier, String attribut) throws Exception{
		Class classe=atrier[0].getClass();
		Field field = classe.getDeclaredField(attribut);
		field.setAccessible(true);
		int taille=atrier.length;
		Object[] obj=new Object[taille];
		double[] result=new double[taille];
		
		for(int i = 0; i<taille ; i++){
            obj[i]=field.get(atrier[i]);
			result[i]=(double)obj[i];
        }

		for(int i = 0; i<taille ; i++){
            for(int j = 0; j <taille-j ; j++){
                if(result[j]> result[j+1]){
                    double rep = result[j];
                    result[j]=result[j+1];
                    result[j+1]=rep;
                }
            }
        }
        return result;
	}
}