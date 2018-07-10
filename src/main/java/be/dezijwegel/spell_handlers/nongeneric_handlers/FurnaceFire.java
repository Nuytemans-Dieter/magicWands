package be.dezijwegel.spell_handlers.nongeneric_handlers;

import be.dezijwegel.objects.Wizard;
import be.dezijwegel.spell_handlers.SpellHandler;

public class FurnaceFire extends SpellHandler {

    public FurnaceFire(Wizard caster) {
        super(caster);
    }

//    @Override
//    public boolean cast(Player player) {
//        if (super.cast(player)) {
//            Block b = player.getTargetBlock((Set<Material>) null, 10);
//            if (b.getType().equals(Material.FURNACE)) {
//                ((Furnace) b.getState()).setBurnTime((short) 2000);
//                ((Furnace) b.getState()).setCookTime((short) 2000);
//                ((Furnace) b.getState()).update();
//                super.setCast(player);
//                return true;
//            } else {
//                player.sendMessage("§3[magicWands]§cThis spell can only be cast on a furnace!");
//                return false;
//            }
//        }
//        return false;
//    }
}
