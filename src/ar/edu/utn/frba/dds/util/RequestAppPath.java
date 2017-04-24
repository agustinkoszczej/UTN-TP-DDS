package ar.edu.utn.frba.dds.util;

public enum RequestAppPath {
    student ("/student"),
    assignments ("/student/assignments");

    private final String name;       

    private RequestAppPath(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
