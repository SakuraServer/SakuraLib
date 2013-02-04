/**
 * SakuraLib - Package: net.syamn.utils.cb.inv
 * Created: 2013/02/05 6:25:23
 */
package net.syamn.utils.cb.inv;

import net.minecraft.server.v1_4_R1.ContainerChest;
import net.minecraft.server.v1_4_R1.EntityHuman;
import net.minecraft.server.v1_4_R1.IInventory;

/**
 * CBContainerChest (CBContainerChest.java)
 */
public class CBContainerChest extends ContainerChest{
    public IInventory inv;
    
    public CBContainerChest(IInventory i1, IInventory i2){
        super(i1, i2);
        inv = i2;
        inv.f();
    }

    @Override
    public void b(EntityHuman entityHuman){
        // Don't send close signal
    }
}
