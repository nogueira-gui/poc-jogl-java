package components;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Scene implements GLEventListener {
    private float xMin, xMax, yMin, yMax, zMin, zMax;

    private float rot_x, rot_y, rot_z = 0.0f;

    GLU glu;

    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        glu = new GLU();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -1;
        xMax = yMax = zMax = 1;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();
        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(0, 0, 0, 1);
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity(); //lê a matriz identidade

        /*
            desenho da cena
        *
        */
        //draw lines XYZ perspective
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(1, 0, 0); //red
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(xMax + 1, 0, 0);
        gl.glColor3f(0, 1, 0); //green
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, yMax, 0);
        gl.glColor3f(0, 0, 1); //blue
        gl.glVertex3f(0, 0, zMin);
        gl.glVertex3f(-1, -1, zMax);
        gl.glEnd();

        drawCube(gl, 0.50F, 0.0F, 0.0F, 0.0F, this.rot_x, this.rot_y, this.rot_z);

        gl.glFlush();
    }

    public void rotacionarX() {
        this.rot_x = 5.0f + this.rot_x;
    }
    public void rotacionarY() {
        this.rot_y = 5.0f + this.rot_y;
    }
    public void rotacionarZ() {
        this.rot_z = 5.0f + this.rot_z;
    }

    private static void drawCube(GL2 gl, float tamanho, float dx, float dy, float dz, float rot_x, float rot_y, float rot_z) {
        float d = tamanho / 2.0f;

        float[][] vertices = {
                {-d, -d, -d},
                {d, -d, -d},
                {d, d, -d},
                {-d, d, -d},
                {-d, -d, d},
                {d, -d, d},
                {d, d, d},
                {-d, d, d}
        };

        // Aplicar rotações
        float cosY = (float) Math.cos(Math.toRadians(rot_y));
        float sinY = (float) Math.sin(Math.toRadians(rot_y));
        float cosX = (float) Math.cos(Math.toRadians(rot_x));
        float sinX = (float) Math.sin(Math.toRadians(rot_x));
        float cosZ = (float) Math.cos(Math.toRadians(rot_z));
        float sinZ = (float) Math.sin(Math.toRadians(rot_z));

        for (int i = 0; i < vertices.length; i++) {
            float[] vertex = vertices[i];
            float x = vertex[0];
            float y = vertex[1];
            float z = vertex[2];

            // Rotação em torno do eixo Y
            float new_x = x * cosY - z * sinY;
            float new_z = x * sinY + z * cosY;
            x = new_x;
            z = new_z;

            // Rotação em torno do eixo X
            float new_y = y * cosX - z * sinX;
            z = y * sinX + z * cosX;
            y = new_y;

            // Rotação em torno do eixo Z
            new_x = x * cosZ - y * sinZ;
            float new_y2 = x * sinZ + y * cosZ;
            x = new_x;
            y = new_y2;

            // Atualiza as coordenadas do vértice após as rotações
            vertex[0] = x + dx;
            vertex[1] = y + dy;
            vertex[2] = z + dz;
        }

        int[][] edges = {
                {0, 1}, {1, 2}, {2, 3}, {3, 0},
                {4, 5}, {5, 6}, {6, 7}, {7, 4},
                {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };

        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(1, 1, 1); // Branco

        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            float[] vertex1 = vertices[v1];
            float[] vertex2 = vertices[v2];
            gl.glVertex3fv(vertex1, 0);
            gl.glVertex3fv(vertex2, 0);
        }

        gl.glEnd();
    }


    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();

        //evita a divisão por zero
        if (height == 0) height = 1;
        //calcula a proporção da janela (aspect ratio) da nova janela
        float aspect = (float) width / height;

        //seta o viewport para abranger a janela inteira
        gl.glViewport(0, 0, width, height);

        //ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //lê a matriz identidade

        //Projeção ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
        if (width >= height)
            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
        else
            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);

        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //lê a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }
}





