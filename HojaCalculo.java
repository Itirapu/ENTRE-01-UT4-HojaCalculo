
/**
 *  Un objeto de esta clase representa a una sencilla
 *  hoja de cálculo. La hoja tiene hasta un máximo de 3 filas (no más)
 *  En cada fila la empresa "apunta" los ingresos y gastos en 
 *  una determinada fecha
 * 
 * @author -   
 *  
 */
public class HojaCalculo
{
    private String nombre;
    private Fila fila1;
    private Fila fila2;
    private Fila fila3;

    /**
     * Constructor  
     * Crea la hoja de cálculo con el nombre indicado 
     * e inicializa las filas al valor null (inicialmente
     * la hoja se crea sin filas)
     */
    public HojaCalculo(String nombre){
        this.nombre = nombre;
        this.fila1 = null;
        this.fila2 = null;
        this.fila3 = null;
    }

    /**
     * accesor para el nombre de la hoja
     */
    public String getNombre(){
        return this.nombre;

    }

    /**
     * accesor para la fila1
     */
    public Fila getFila1(){
        return fila1;

    }

    /**
     * accesor para la fila2
     */
    public Fila getFila2(){
        return fila2;

    }

    /**
     * accesor para la fila3
     */
    public Fila getFila3(){
        return fila3;

    }

    /**
     * Devuelve el nº de filas de la hoja
     * (dependerá de cuántas filas estén a null)
     */
    public int getNumeroFilas(){
        int tempo = 0;
        if(fila1 != null){
            tempo = tempo + 1;
        }
        if(fila2 != null){
            tempo = tempo + 1;
        }
        if(fila3 != null){
            tempo = tempo + 1;
        }
        return tempo;
    }

    /**
     * Devuelve true si la hoja está completa
     * (tiene exactamente 3 filas)
     */
    public boolean hojaCompleta(){
        if(getNumeroFilas() == 3){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Se añade una nueva fila a la hoja
     * Si la hoja está completa se muestra el mensaje "FilaX no se puede añadir en HOJAX"
     * Si no está completa se añade la fila a la hoja teniendo en cuenta
     * si se añade como primera, segunda o tercera fila (no han de quedar huecos)
     */
    public void addFila(Fila fila){
        if(hojaCompleta()){
            System.out.printf("%s no se puede añadir en %s", fila.getId(), this.nombre);
        }else if(fila1 == null){
            fila1 = fila;
        }else if(fila2 == null){
            fila2 = fila;

        }else{
            if(fila3 == null){
                fila3 = fila;
            }
        }
    }

    /**
     * Dada la información a guardar en una fila el método
     * crea la fila y la añade a la hoja
     * (evita repetir código)
     */
    public void addFila(String id, Fecha fecha, double ingresos, double gastos) {
        Fila nuevaFila = new Fila(id, fecha, ingresos, gastos);
        this.addFila(nuevaFila);
    }

    /**
     * Calcula y devuelve el total de ingresos entre
     * todas las filas que incluye la hoja
     */
    public double getTotalIngresos(){
        double tempo = 0;
        if(fila1 != null){
            tempo = tempo + fila1.getIngresos();
        }
        if(fila2 != null){
            tempo = tempo + fila2.getIngresos();
        }
        if(fila3 != null){
            tempo = tempo + fila3.getIngresos();
        }
        return tempo;
    }

    /**
     * Calcula y devuelve el total de gastos
     * entre todas las filas que incluye la hoja
     */
    public double getTotalGastos(){
        double tempo = 0;
        if(fila1 != null){
            tempo = tempo + fila1.getGastos();
        }
        if(fila2 != null){
            tempo = tempo + fila2.getGastos();
        }
        if(fila3 != null){
            tempo = tempo + fila3.getGastos();
        }
        return tempo;
    }

    /**
     * Calcula y devuelve el total del beneficio
     * entre todas las filas que incluye la hoja
     */
    public double getBeneficio(){
        double tempo = 0;
        if(fila1 != null){
            tempo = tempo + fila1.getBeneficio();
        }
        if(fila2 != null){
            tempo = tempo + fila2.getBeneficio();
        }
        if(fila3 != null){
            tempo = tempo + fila3.getBeneficio();
        }
        return tempo;
    }

    /**
     * Representación textual de la hoja
     * con el formato exacto que indica el enunciado
     */
    public String toString(){
        String tempo = String.format("%s\n%8s%15s%16s%16s%16s",this.nombre,"","FECHA","INGRESOS","GASTOS","BENEFICIO");

        if(getNumeroFilas() == 1){
            tempo = tempo + "\n" + fila1.toString();
        }else{
            if(getNumeroFilas() == 2){
                tempo = tempo + "\n" + fila1.toString();
                tempo = tempo + "\n" + fila2.toString();
            }else{
                tempo = tempo + "\n" + fila1.toString();
                tempo = tempo + "\n" + fila2.toString();
                tempo = tempo + "\n" + fila3.toString();
            }
        }

        tempo = tempo + "\n" + "---------------------------------------------------------------------------------";
        String tempo2 = String.format("%38s€%15s€%15s€",this.getTotalIngresos(),this.getTotalGastos(),this.getBeneficio());
        tempo = tempo + '\n' + tempo2;

        return tempo; 
        
        
    }
    /**
     * Devuelve un duplicado de la hoja actual.
     * El nombre de la hoja duplicada será "Duplicada HojaX"
     * Al duplicar la hoja se duplicarán también las filas que contenga
     */
    public HojaCalculo duplicarHoja(){
        HojaCalculo hojaNueva = new HojaCalculo("Duplicada " + this.nombre);
        
        if(getNumeroFilas() == 1){
            Fila nuevaFila1 = fila1.duplicar();
            hojaNueva.addFila(nuevaFila1);
        }else{
            if(getNumeroFilas() == 2){
                Fila nuevaFila1 = fila1.duplicar();
                hojaNueva.addFila(nuevaFila1);
                Fila nuevaFila2 = fila2.duplicar();
                hojaNueva.addFila(nuevaFila2);
            }else{
                Fila nuevaFila1 = fila1.duplicar();
                hojaNueva.addFila(nuevaFila1);
                Fila nuevaFila2 = fila2.duplicar();
                hojaNueva.addFila(nuevaFila2);
                Fila nuevaFila3 = fila3.duplicar();
                hojaNueva.addFila(nuevaFila3);
            }
        }
        
        return hojaNueva;
    }

}
