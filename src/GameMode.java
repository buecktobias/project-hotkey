public enum GameMode {
    Peaceful("Peaceful", 1000.0,99999999),
    Easy("Easy", 1.0,1000),
    Normal("Normal", 0.1,200),
    Hard("Hard",0,100);
    public String name;
    public double regeneration;
    public int eachFrameEnemySpawns;
    GameMode(String name, double regeneration, int eachFrameEnemySpawns){
        this.name = name;
        this.regeneration = regeneration;
        this.eachFrameEnemySpawns = eachFrameEnemySpawns;
    }

}
