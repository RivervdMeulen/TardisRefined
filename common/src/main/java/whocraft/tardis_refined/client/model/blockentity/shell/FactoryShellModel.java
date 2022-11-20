package whocraft.tardis_refined.client.model.blockentity.shell;// Made with Blockbench 4.5.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.world.entity.Entity;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;

public class FactoryShellModel extends HierarchicalModel implements IShellModel {

	private final ModelPart root;
	private final ModelPart leftDoor;
	private final ModelPart rightDoor;

	public FactoryShellModel(ModelPart root) {
		this.root = root.getChild("root");
		this.leftDoor = this.root.getChild("left_door");
		this.rightDoor = this.root.getChild("right_door");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 28).addBox(-10.0F, -3.0F, -8.0F, 20.0F, 3.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-10.0F, -44.0F, -8.0F, 20.0F, 7.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(54, 57).addBox(-5.0F, -37.0F, -8.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(54, 52).addBox(-5.0F, -5.0F, -8.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(35, 52).addBox(-8.0F, -37.0F, -6.0F, 1.0F, 34.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(95, 45).addBox(-7.0F, -37.0F, 9.0F, 14.0F, 34.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).addBox(7.0F, -37.0F, -6.0F, 1.0F, 34.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(19, 52).addBox(-7.0F, -37.0F, -6.0F, 14.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 57).addBox(-7.0F, -5.0F, -6.0F, 14.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(81, 0).mirror().addBox(8.0F, -37.0F, -3.0F, 2.0F, 34.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(81, 0).addBox(-10.0F, -37.0F, -3.0F, 2.0F, 34.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -2.0F));

		PartDefinition left_door = root.addOrReplaceChild("left_door", CubeListBuilder.create().texOffs(70, 97).addBox(0.0F, -15.0F, -0.5F, 7.0F, 30.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -20.0F, -5.6F));

		PartDefinition right_door = root.addOrReplaceChild("right_door", CubeListBuilder.create().texOffs(95, 81).addBox(-7.0F, -15.0F, -0.5F, 7.0F, 30.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -20.0F, -5.6F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(70, 52).addBox(-1.0F, -17.0F, -5.0F, 2.0F, 34.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.0F, 11.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {
	}

	@Override
	public void setDoorPosition(boolean open) {
		if (open) {
			this.leftDoor.yRot = 250f;
			this.rightDoor.yRot = -250f;
		} else {
			this.leftDoor.yRot = 0;
			this.rightDoor.yRot = 0;
		}
	}
}