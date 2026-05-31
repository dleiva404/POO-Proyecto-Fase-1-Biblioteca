package biblioteca.modelos.material;

public abstract class MaterialEscrito extends Material {

    private int numeroPaginas;
    private String editorial;

    public MaterialEscrito(String codigoIdentificacion, String titulo, String categoria, String ubicacionFisica, int cantidadTotal, int numeroPaginas, String editorial) {
        super(codigoIdentificacion, titulo, categoria, ubicacionFisica, cantidadTotal);
        this.numeroPaginas = numeroPaginas;
        this.editorial = editorial;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return super.toString() +
                " MaterialEscrito{" +
                "numeroPaginas=" + numeroPaginas +
                ", editorial='" + editorial + '\'' +
                '}';
    }
}
