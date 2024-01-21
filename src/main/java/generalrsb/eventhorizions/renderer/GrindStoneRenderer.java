package generalrsb.eventhorizions.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import generalrsb.eventhorizions.EventHorizions;
import generalrsb.eventhorizions.Profiler;
import generalrsb.eventhorizions.blocks.blockentities.GrindstoneBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.model.EmptyModel;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.geometry.UnbakedGeometryHelper;
import net.minecraftforge.fml.common.Mod;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;

public class GrindStoneRenderer implements BlockEntityRenderer<GrindstoneBlockEntity> {

    BlockRenderDispatcher dispatcher;
    BlockEntityRendererProvider.Context context;
    ModelPart bottomModelPart;
    ModelPart topModelPart;

    ModelPart lower;
    public GrindStoneRenderer(BlockEntityRendererProvider.Context context){
        this.context = context;

        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(1f,0f,1f,14f,6f,14f), PartPose.ZERO);

        //, PartPose.offset(0.0F, 9.0F, 1.0F)
        bottomModelPart = LayerDefinition.create(meshdefinition, 64, 22).bakeRoot();

        meshdefinition = new MeshDefinition();
        partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 0).addBox(1f,6f,1f,14f,6f,14f),PartPose.ZERO);
        partdefinition.addOrReplaceChild("handel", CubeListBuilder.create().texOffs(0, 0).addBox(12f,12f,1f,1f,3f,1f),PartPose.ZERO);
        //, PartPose.offset(0.0F, 9.0F, 1.0F)
        topModelPart = LayerDefinition.create(meshdefinition, 64, 22).bakeRoot();
    }

    @Override
    public void render(GrindstoneBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedOverlay, int packedLight) {

        dispatcher = context.getBlockRenderDispatcher();
        VertexConsumer vertexConsumer =buffer.getBuffer(RenderType.entityTranslucent(new ResourceLocation(EventHorizions.MODID,"textures/entity/grindstone.png")));

        bottomModelPart.render(poseStack,vertexConsumer,combinedOverlay,packedLight);
        poseStack.pushPose();
        poseStack.translate(0.5f,0,0.5f);
        poseStack.mulPose(Axis.YN.rotationDegrees(360*blockEntity.getProgress()));
        poseStack.translate(-0.5f,0,-0.5f);
        topModelPart.render(poseStack,vertexConsumer,combinedOverlay,packedLight);
        poseStack.popPose();
        vertexConsumer.endVertex();

        if (blockEntity.getItemStackHandler().getStackInSlot(0)!= ItemStack.EMPTY) {
            poseStack.pushPose();
            poseStack.translate(0.5, 0.9f, 0.5);


            poseStack.scale(0.3f, 0.3f, 0.3f);
            context.getItemRenderer().renderStatic(blockEntity.getItemStackHandler().getStackInSlot(0), ItemDisplayContext.FIXED, combinedOverlay, packedLight, poseStack, buffer, blockEntity.getLevel(), (int) blockEntity.getBlockPos().asLong());
            poseStack.translate(0, 0.4f, 0);
            context.getFont().drawInBatch(String.valueOf(blockEntity.getItemStackHandler().getStackInSlot(0).getCount()), 50, 50, 0xFFFFFFFF, false, poseStack.last().pose(), buffer, Font.DisplayMode.POLYGON_OFFSET, combinedOverlay, packedLight);
            poseStack.popPose();
        }


    }
}
