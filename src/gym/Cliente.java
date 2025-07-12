package gym;

public class Cliente {
    private int id;
    private String nombre;
    private int edad;
    private String membresia;

    public Cliente(int id, String nombre, int edad, String membresia) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.membresia = membresia;
    }

    //Getters
    public int getId() { 
    	return id; 
    	}
    public String getNombre() { 
    	return nombre; 
    	}
    public int getEdad() {
    	return edad; 
    	}
    public String getMembresia() {
    	return membresia;
    	}
    
    //Setters
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    	}
    public void setEdad(int edad) { 
    	this.edad = edad; 
    	}
    public void setMembresia(String membresia) { 
    	this.membresia = membresia;
    	}
}
