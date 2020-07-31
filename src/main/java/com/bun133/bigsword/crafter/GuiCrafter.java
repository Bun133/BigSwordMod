package com.bun133.bigsword.crafter;

import com.bun133.bigsword.ModMain;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCrafter extends GuiContainer {

    private final int guiXSize=199;
    private final int guiYSize=222;

    private final ResourceLocation TEXTURE=new ResourceLocation(ModMain.MOD_NAME, "textures/gui/crafttable.png");
    private final InventoryPlayer player;
    private final TileEntityCrafter tileEntity;
    private final EntityPlayer entityPlayer;
    public GuiCrafter(EntityPlayer player, TileEntityCrafter tileEntity) {
        super(new ContainerCrafter(player.inventory,tileEntity));
        this.player=player.inventory;
        this.entityPlayer=player;
        this.tileEntity=tileEntity;
//        this.setGuiSize(199,222);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//        @SuppressWarnings("ConstantConditions")
//        String titleName=this.tileEntity.getDisplayName().getUnformattedText();
//        this.fontRenderer.drawString(titleName,convertPos_x(this.xSize/2-this.fontRenderer.getStringWidth(titleName))-5,convertPos_y(6),4210752);
//        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(),convertPos_x(7), convertPos_y(ySize - 96 + 2),4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f,1.0f,1.0f);
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(TEXTURE);
//        this.mc.renderEngine.bindTexture(TEXTURE);
        int center_x=(width/2) - guiXSize /2;
        int center_y=(height/2) - guiYSize /2;
        drawTexturedModalRect(center_x,center_y,0,0,this.guiXSize,this.guiYSize);
    }

    @Override
    public int getXSize() {
        return guiXSize;
    }

    @Override
    public int getYSize() {
        return guiYSize;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public final int guiXCentre = guiXSize /2 ;
    public final int guiYCentre = guiYSize /2 ;
    public final double zoom_x = guiXSize / xSize;
    public final double zoom_y = guiYSize / ySize;
    public int convertPos_x(int before_x){
        /*if(before_x>guiXCentre){
            return (int) (guiXCentre + (before_x-guiXCentre) * zoom_x);
        }else if(before_x<guiXCentre){
            return guiXCentre - before_x *zoom_x
        }*/

        return (int) (guiXCentre + (before_x - guiXCentre) *zoom_x);
        //return before_x/**xSize/guiXSize*/;
    }

    public int convertPos_y(int before_y){
        return (int) (guiYCentre + (before_y - guiYCentre) * zoom_y);
        //return before_y/**ySize/guiYSize*/;
    }

    @Override
    public void onGuiClosed() {
        tileEntity.OnGuiClose(this.entityPlayer);
    }
}