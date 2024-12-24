package bsu_rfe_java_group7_HvalMaxim.lab1_varA19;

public class IceCream extends Food
{
    private String syrup;
    public IceCream(String syrup)
    {
        super("Мороженое");
        this.syrup = syrup;
    }
    public void consume()
    {
        System.out.println(this + " съели ;(");
    }
    public String getSyrup()
    {
        return syrup;
    }
    public void setSyrup(String syrup)
    {
        this.syrup = syrup;
    }
    /*public boolean equals(Object arg0)
    {
        if (super.equals(arg0))
        {
            if (!(arg0 instanceof IceCream)) return false;
            return syrup.equals(((IceCream)arg0).syrup);
        }
        else
            return false;
    }*/
    public String toString()
    {
        return super.toString() + " с сиропом: " + syrup.toUpperCase();
    }
}
