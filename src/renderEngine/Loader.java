package renderEngine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    int vaoID = createVAO();
    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();

    public rawModel loadToVAO(float[] pos) {

        storeDataInAttributesList(0, pos);
        unbindVAO();
        return new rawModel(vaoID, pos.length/3);
    }

    public void cleanUp() {
        for (int vao:vaos) {
            GL30.glDeleteVertexArrays(vao);
        }
        for (int vbo:vbos) {
            GL15.glDeleteBuffers(vbo);
        }
    }

    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        vaos.add(vaoID);
        return vaoID;
    }

    private void storeDataInAttributesList(int attributesNumber, float[] data) {
        int vboID = GL15.glGenBuffers();
        final var add = vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vaoID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributesNumber, 3, GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
