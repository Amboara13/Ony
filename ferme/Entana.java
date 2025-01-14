package maison;
import java.awt.Rectangle;
public class Entana extends Rectangle
{
    int genre;
    //0 si Fandriana
    //1 si Sakafo alika
    //2 si Sakafo Saka
    public Entana(int a,int b,int c,int d,int e)
    {
        super.x=a;
        super.y=b;
        super.width=c;
        super.height=d;
        this.genre=e;
    }  
    public int getGenre()
    {
        return this.genre;
    }
}