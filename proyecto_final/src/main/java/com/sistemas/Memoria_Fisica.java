package com.sistemas;

public class Memoria_Fisica {
    // La memoria física se compone de marcos para página
    // estos marcos tienen un tamaño definido, deben tener
    // el mismo tamaño que las páginas

    private final int tamano_de_marco;  // En bytes
    private final int numero_de_marcos;
    private final int tamano_en_bytes;  // Se determinaría como (numero de marcos)*(tamaño de marco)
    private final int[] memoria;  //Un arreglo de números que contendrá el número de página que le hace referencia
    private int marcos_usados = 0;

    private Sched_Aging sched_aging;

    /**
    *  Constructor de la memoria física
    *  @param tamano_de_marco El tamaño de marco en bytes (deberá ser equivalente al tamaño de página)
     * @param numero_de_marcos El número de marcos (celdas) que contendrá la memoria física
    */
    public Memoria_Fisica(int tamano_de_marco, int numero_de_marcos){
        this.tamano_de_marco = tamano_de_marco;
        this.numero_de_marcos = numero_de_marcos;

        // Se inicializa el arreglo que simulará la memoria
        this.memoria = new int[numero_de_marcos];
        // Se inicializa con 0
        for (int i = 0; i < numero_de_marcos; i++)
            memoria[i] = 0;
        this.tamano_en_bytes = numero_de_marcos*tamano_de_marco;
    }

    public int getTamano_en_bytes() {
        return tamano_en_bytes;
    }

    public int getNumero_de_marcos() {
        return numero_de_marcos;
    }

    public int getTamano_de_marco() {
        return tamano_de_marco;
    }

    /** @param numero_de_marco Índice para mapear el número de página que referencia a dicho marco **/
    public int getPaginaFromMarco(int numero_de_marco) {
        return memoria[numero_de_marco];
    }

    public void addPagina(int numero_de_marco, int numero_de_pagina){
        memoria[numero_de_marco] = numero_de_pagina;
        incMarcos_usados();
    }

    public void actualizarReferenciado(int numero_marco){
        sched_aging.actualizarReferenciado(numero_marco);
    }

    public void actualizarRegistrosAging(){
        sched_aging.actualizarRegistrosAging();
    }

    public void incMarcos_usados(){
        this.marcos_usados++;
    }

    public float getPorcentajeDeUso() { // Regresa el porcentaje 1.0 - 0.0
        return (float)marcos_usados/numero_de_marcos;
    }

    //    private final int tamano_de_memoria; // Tamaño de la memoria física - Se recibirá 1024 bytes por ejemplo
//    private int[] memoria; // Memoria física - Se guardara en un arreglo de 1024 bytes  (almacena los índices de página que almacena)
//    private final short tamano_de_marco; // Tamaño de los marcos de la memoria física - Debe ser igual al tamaño de página
//    private int porcentaje_de_uso; // Porcentaje de uso de la memoria física - Este porcentaje no es de 0-100, sino de 0-1024
//    private Sched_Aging aging;
//
//
//    public Memoria_Fisica(int tamano_de_memoria, int tamano_de_marco) {
//        this.tamano_de_memoria = tamano_de_memoria; //Número de marcos
//        this.memoria = new int[tamano_de_memoria];
//        this.tamano_de_marco = (short) tamano_de_marco;
//
//        aging = new Sched_Aging(tamano_de_memoria);
//
//        // Creo que es conveniente que no se inicialicen todos los campos, sino
//        // que queden como null inicialmente (de mientras lo dejo igual)
//
//        // En este caso, de inicializar con 0s, entonces no habría forma correcta de asignar el índice de página 0
//        // Por lo que se tendría que comenzar la indización de páginas desde 1, y al regresar el dato desde la
//        // función de getMemoria_con_pagina() se tendrá que restar un 1 al índice para devolverlo a una forma
//        // más compatible con los demás módulos del sistema
//        for (int i = 0; i < tamano_de_memoria; i++) {
//            memoria[i] = 0;
//        }
//    }
//
//
//    // Al comienzo debe haber un fallo de página por cada marco, debido a que empieza vacío
    public void falloDePagina(Tabla_de_Paginas tabla, Memoria_Virtual memoria_virtual, int nuevaPagina){
        // Se debe de accionar al algoritmo de reemplazo de páginas
        int marco_de_pagina_a_reemplazar = sched_aging.seleccionarReemplazable() + 1;

        // Se debe obtener el número de página a reemplazar a partir del marco obtenido
        int numero_pagina_a_reemplazar = getPaginaFromMarco(marco_de_pagina_a_reemplazar);

        // Una vez determinado se debe modificar el bit de presente/ausente de la página a reemplazar
        tabla.setBitPresenteAusente(numero_pagina_a_reemplazar);

        // Se reemplaza el anterior número página por el nuevo número de página

        // Si el marco estaba "vacío" (marcado con 0) entonces se debe incrementar el porcentaje de uso
        // si ya estaba ocupado anteriormente simplemente se reemplaza, ya que el porcentaje será el mismo
        if(this.memoria[marco_de_pagina_a_reemplazar] == 0){
            memoria[marco_de_pagina_a_reemplazar] = nuevaPagina;
            marcos_usados++;
        } else {
            memoria[marco_de_pagina_a_reemplazar] = nuevaPagina;
        }

        // Una vez añadido a la memoria física se añade a la memoria virtual

        //memoria_virtual

        System.out.println("Reemplazo: " + nuevaPagina + " -> " + numero_pagina_a_reemplazar + "en marco "
                + marco_de_pagina_a_reemplazar);

    }
//
//    public void actualizarReferenciado(int numero_marco){
//        aging.actualizarReferenciado(numero_marco);
//    }
//
//    public void actualizarRegistrosAging(){
//        aging.actualizarRegistrosAging();
//    }
//
//
//    public short getTamanoDeMarco() {
//        return tamano_de_marco;
//    }
//
//    public byte getTamanoDeMarco_bytes() {
//        return (byte) Integer.bitCount(tamano_de_marco - 1);
//    }
//
//    public int getTamanoDeMemoria() {
//        return tamano_de_memoria;
//    }
//
//    // Con esto se devuelve el contenido de un marco (que debería ser el número de página que referencia)
//    // dado un número de marco
//    public int getMemoria_con_pagina(int numero_marco) {
//        return memoria[numero_marco] - 1;
//    }
//
//    public boolean getMemoriaLlena() {
//        return porcentaje_de_uso == tamano_de_memoria;
//    }
//
//    // En el contexto de este programa el dato sería el índice (o al número de página que apunta a este marco)
//    // Devuelve 'true' cuando se ha añadido contenido a un marco de forma exitosa
//    // Devuelve 'false' cuando el marco no está vacío, por lo que debería mandarse a llamar al algoritmo
//    // de reemplazo de páginas (puede hacerse aquí mismo o en otra función
//    public boolean addMemoria_con_pagina(int numero_de_marco, int dato) {
//        if ((numero_de_marco < tamano_de_memoria) && (memoria[numero_de_marco] == 0)) {
//            memoria[numero_de_marco] = dato;
//            if (dato == 0) { // Si el dato es 0, se decrementa el porcentaje de uso ya que se esta liberando memoria
//                porcentaje_de_uso--;
//            } else {
//                porcentaje_de_uso++;
//            }
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//
//    // Función para eliminar el contenido de un índice (marco)
//    public boolean removeMemoria_con_pagina(int numero_de_marco) {
//        if ((numero_de_marco < tamano_de_memoria) & (memoria[numero_de_marco] == 0)) {
//            memoria[numero_de_marco] = 0;
//            porcentaje_de_uso--;
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//
//    public float getPorcentajeDeUso() { // Regresa el porcentaje 1.0 - 0.0
//        return (float)porcentaje_de_uso/tamano_de_memoria;
//    }
//
//    public void emptyMemoria() {
//        for (int i = 0; i < tamano_de_memoria; i++) {
//            memoria[i] = 0;
//        }
//        porcentaje_de_uso = 0;
//    }
}
