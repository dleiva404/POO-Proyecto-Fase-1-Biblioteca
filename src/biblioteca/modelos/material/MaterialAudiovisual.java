package biblioteca.modelos.material;

public abstract class MaterialAudiovisual extends Material {

    private int duracion;

    public MaterialAudiovisual(String codigoIdentificacion, String titulo, String categoria, String ubicacionFisica, int cantidadTotal, int duracion) {
        super(codigoIdentificacion, titulo, categoria, ubicacionFisica, cantidadTotal);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    @Override
    public String toString() {
        return super.toString() +
                " MaterialAudiovisual{" +
                "duracion=" + duracion +
                '}';
    }
}
