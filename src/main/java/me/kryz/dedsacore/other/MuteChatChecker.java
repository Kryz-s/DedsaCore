package me.kryz.dedsacore.other;

public final class MuteChatChecker {
    private boolean muteCheck;

    public MuteChatChecker() {
        this.muteCheck = false;
    }

    public void setMuteCheck(final boolean bool){
        this.muteCheck = bool;
    }

    public boolean isMuteChat(){
        return this.muteCheck;
    }
}
