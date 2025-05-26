package Consultores;

public class ObjDatos {
    private int id;
    private String nombre;
    private int edad;
    private String tipo;
    private String descripcion;
    private String estado;

    //Metodo constructor
    public ObjDatos(int id, String nombre, int edad, String tipo, String descripcion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  public String getNombre() {
        return nombre;
}
public void setNombre(String nombre) {
        this.nombre = nombre;
}
public int getEdad() {
        return edad;
}
public void setEdad(int edad) {
        this.edad = edad;
}
public String getTipo() {
        return tipo;
}
public void setTipo(String tipo) {
        this.tipo = tipo;
}
public String getDescripcion() {
        return descripcion;
}
public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}