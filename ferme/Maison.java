package maison;
import java.awt.Rectangle;
import java.util.Vector;
public class Maison extends Rectangle
{
    Vector<Entana> ensemble_entana;
    Vector<Zavamananaina> zavamananaina;
    
    public Maison(int a,int b,int c,int d, Vector<Entana> ensemble_entana, Vector<Zavamananaina> zavamananaina )
    {
        super.x=a;
        super.y=b;
        super.width=c;
        super.height=d;
        this.ensemble_entana=ensemble_entana;
        this.zavamananaina=zavamananaina;
    } 
    public Vector<Entana> getEnsembleEntana()
    {
        return this.ensemble_entana;
    }
    public Vector<Zavamananaina> getZavamananaina()
    {
        return this.zavamananaina;
    }
}