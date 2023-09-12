class UPS {
    private int batteryCap;
    private int loadCap;
    public int load;
    private float time_on_fullLoad;

    public UPS(int BC, int LC, int L, float ToFL){
        this.batteryCap = BC; // the measure is Ah
        this.loadCap = LC; // measure is Watts
        this.load = L;// measure is Watts
        this.time_on_fullLoad = ToFL; // measure is in hours

    }

}
