public class Flags {
	private boolean nextLevel = false;
	private boolean hitEnemy = false;
	
	public Flags() {
	
	}
	
	public void setNextLevelFlag(boolean newFlag) {
	    nextLevel = newFlag;
	}
	
	public void setHitEnemyFlag(boolean newFlag) {
	    hitEnemy = newFlag;
	}
	
	public boolean getNextLevelFlag() {
	    return nextLevel;
	}
	
	public boolean getHitEnemyFlag() {
	    return hitEnemy;
	}
}
