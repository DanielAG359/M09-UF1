package iticbcn.xifratge;

public class TextXifrat {
    private byte[] mordiscos;
    public TextXifrat(byte[] mordiscos){
        setBytes(mordiscos);
    }
    @Override
    public String toString(){
        return new String(mordiscos);
    };
    public byte[] getBytes(){
        return mordiscos;
    };
    public void setBytes(byte[] mordiscos){
        this.mordiscos = mordiscos;
    };
}
