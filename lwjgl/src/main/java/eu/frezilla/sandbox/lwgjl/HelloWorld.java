package eu.frezilla.sandbox.lwgjl;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;

public class HelloWorld {
    
    // Window handle
    private long window;

    public static void main(String[] args) {
        new HelloWorld().run();
    }

    private void init() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void loop() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void run() {
        System.out.println("Hello world with LWJGL");
        
        init();
        loop();
        
        Callbacks.glfwFreeCallbacks(window);
        GLFW.glfwDestroyWindow(window);
        
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

}
