public interface FireSensitive extends Attackable {
    void setFireDamage(double fireDamage);
    double getFireDamage();
    default void subtractFireDamageFromLife(){
        setLife(getLife() - getFireDamage());
        setFireDamage(getFireDamage() * 0.99);
    }
}
