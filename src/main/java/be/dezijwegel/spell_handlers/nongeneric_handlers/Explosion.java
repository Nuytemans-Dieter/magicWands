package be.dezijwegel.spell_handlers.nongeneric_handlers;

import be.dezijwegel.objects.Wizard;
import be.dezijwegel.spell_handlers.SpellHandler;

public class Explosion extends SpellHandler {

    private int radius;

    public Explosion(int radius, Wizard caster) {
        super(caster);
        this.radius = radius;
    }

//    @Override
//    public boolean cast(Player player) {
//        if (super.cast(player)) {
//            Location loc = player.getTargetBlock((Set<Material>) null, 15).getLocation();
//            player.getWorld().createExplosion(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), size, false, false);
//            super.setCast(player);
//            return true;
//        }
//        return false;
//    }
}
