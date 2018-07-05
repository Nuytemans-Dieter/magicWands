package be.dezijwegel.spell_handlers.generic_handlers.handlers;

import be.dezijwegel.objects.PlayerData;
import be.dezijwegel.spell_handlers.generic_handlers.TransformHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Set;

public class TransformBlock extends TransformHandler {

    private final Material material;

    public TransformBlock(PlayerData caster, Block object, Block newObject, Material material) {
        super(caster, object, newObject);
        this.material = material;
    }

    /**
     * @deprecated
     */
    public boolean cast(Player player) {
        if (super.cast(player)) {
            Block b = player.getTargetBlock((Set<Material>) null, 15);
            if (!b.getType().equals(Material.AIR)) {
                b.setType(material);
                super.setCast(player);
                return true;
            } else {
                player.sendMessage("§3[magicWands]§cThis spell has to be cast on a block!");
            }
        }
        return false;
    }


    @Override
    public void transform(Object object, Object newObject) {
        Block oldBlock = (Block) object;
        oldBlock.setType(material);

    }


}
