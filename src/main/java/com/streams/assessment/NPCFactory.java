package com.streams.assessment;

public class NPCFactory {

	public Object createNPC() throws Throwable {
		String className = "DynamicNPC";
		String javaCode = "public class DynamicNPC {"
				+ " public void greet() { System.out.println(\"Hello, I am an NPC!\"); } }";

		Class<?> npcClass = InMemoryJavaCompiler.compile(className, javaCode);
		return npcClass.getDeclaredConstructor().newInstance();
	}
}
