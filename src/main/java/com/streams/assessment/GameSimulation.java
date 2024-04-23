package com.streams.assessment;

public class GameSimulation {
    public static void main(String[] args) {
        GameVectorOperations operations = new GameVectorOperations();
        int[] npcAttributes = {100, 75, 50};  // Health, Speed, Strength
        int[] boost = {10, 5, 5};
        int[] newAttributes = operations.modifyAttributes(npcAttributes, boost);
        System.out.println("New NPC Attributes: " + Arrays.toString(newAttributes));

        NPCFactory factory = new NPCFactory();
        try {
            Object npc = factory.createNPC();
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle greetMethod = lookup.findVirtual(npc.getClass(), "greet",
                                                        MethodType.methodType(void.class));
            greetMethod.invoke(npc);
        } catch (Throwable t) {
            System.err.println("Error in NPC creation or method invocation: " + t.getMessage());
        }
    }
}
