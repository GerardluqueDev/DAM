package objetos;

public class Heroe {
    private String nombreHeroe;
    private String nombreReal;
    private String link;
    private String img;
    private int tamanio;

    public Heroe(String nombreHeroe, String nombreReal, String link, String img, int tamanio) {
        this.nombreHeroe = nombreHeroe;
        this.nombreReal = nombreReal;
        this.link = link;
        this.img = img;
        this.tamanio = tamanio;
    }

    public String getNombreHeroe() {
        return nombreHeroe;
    }

    public void setNombreHeroe(String nombreHeroe) {
        this.nombreHeroe = nombreHeroe;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}
