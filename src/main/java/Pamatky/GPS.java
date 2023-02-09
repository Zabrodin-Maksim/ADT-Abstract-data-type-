package Pamatky;

public class GPS implements Comparable<GPS>{
    private int x;
    private int y;

    public GPS(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    @Override
    public int compareTo(GPS o) {
        long xy1 = toInt(toBinaryArray(x),toBinaryArray(y));
        long xy2 = toInt(toBinaryArray(o.getX()),toBinaryArray(o.getY()));
        return Math.toIntExact(xy1 - xy2);
    }
    private String[] toBinaryArray(int number){
        return Integer.toBinaryString(number).split("");
    }
    private long toInt(String[] prvni,String[] druhy){
        String result = "";
        if(prvni.length>druhy.length){
            for(int i = 0 ; i<prvni.length;i++){
                if(druhy.length<=i) result+=prvni[i];
                else result+=prvni[i] + druhy[i];
            }
        } else {
            for(int i = 0 ; i<druhy.length;i++){
                if(prvni.length<=i) result+=druhy[i];
                else result+=prvni[i] + druhy[i];
            }
        }

        return Long.parseLong(result,2);
    }
}
