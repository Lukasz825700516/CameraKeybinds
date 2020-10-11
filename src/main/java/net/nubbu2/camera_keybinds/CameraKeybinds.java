package net.nubbu2.camera_keybinds;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.mixin.resource.loader.MixinKeyedResourceReloadListener;
import net.fabricmc.loader.transformer.EnvironmentStrippingData;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.options.Perspective;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.JTextComponent;

public class CameraKeybinds implements ModInitializer {

	private static  Perspective lastPerspective = Perspective.FIRST_PERSON;

	private static KeyBinding setPerspectiveFirstPerson;
	private static KeyBinding setPerspectiveThirdPerson;
	private static KeyBinding setPerspectiveThirdPersonReverse;

	private static KeyBinding switchPerspectiveFirstPersonAndLast;
	private static KeyBinding switchPerspectiveThirdPersonAndLast;
	private static KeyBinding switchPerspectiveThirdPersonReverseAndLast;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		setPerspectiveFirstPerson = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.camera_keybinds.first_person_camera",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_I,
				"category.camera_keybinds.camera_keybinds"
		));
		setPerspectiveThirdPerson = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.camera_keybinds.third_person_camera",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_O,
				"category.camera_keybinds.camera_keybinds"
		));
		setPerspectiveThirdPersonReverse = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.camera_keybinds.third_person_reverse_camera",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_P,
				"category.camera_keybinds.camera_keybinds"
		));
		switchPerspectiveFirstPersonAndLast = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.camera_keybinds.switch_first_person_camera",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_B,
				"category.camera_keybinds.camera_keybinds"
		));
		switchPerspectiveThirdPersonAndLast = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.camera_keybinds.switch_third_person_camera",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_N,
				"category.camera_keybinds.camera_keybinds"
		));
		switchPerspectiveThirdPersonReverseAndLast = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.camera_keybinds.switch_third_person_reverse_camera",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_M,
				"category.camera_keybinds.camera_keybinds"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(setPerspectiveFirstPerson.wasPressed()) {
				client.options.method_31043(Perspective.FIRST_PERSON);
			}
			if(setPerspectiveThirdPerson.wasPressed()) {
				client.options.method_31043(Perspective.THIRD_PERSON_BACK);
			}
			if(setPerspectiveThirdPersonReverse.wasPressed()) {
				client.options.method_31043(Perspective.THIRD_PERSON_FRONT);
			}

			if(switchPerspectiveFirstPersonAndLast.wasPressed()) {
			    Perspective perspective = client.options.getPerspective();
			    if (perspective != Perspective.FIRST_PERSON) {
					lastPerspective = perspective;
					client.options.method_31043(Perspective.FIRST_PERSON);
				} else {
					client.options.method_31043(lastPerspective);
				}
			}
			if(switchPerspectiveThirdPersonAndLast.wasPressed()) {
				Perspective perspective = client.options.getPerspective();
				if (perspective != Perspective.THIRD_PERSON_BACK) {
					lastPerspective = perspective;
					client.options.method_31043(Perspective.THIRD_PERSON_BACK);
				} else {
					client.options.method_31043(lastPerspective);
				}
			}
			if(switchPerspectiveThirdPersonReverseAndLast.wasPressed()) {
				Perspective perspective = client.options.getPerspective();
				if (perspective != Perspective.THIRD_PERSON_FRONT) {
					lastPerspective = perspective;
					client.options.method_31043(Perspective.THIRD_PERSON_FRONT);
				} else {
					client.options.method_31043(lastPerspective);
				}
			}
		});
	}
}
