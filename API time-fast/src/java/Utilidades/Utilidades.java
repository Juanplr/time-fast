
package Utilidades;


public class Utilidades {
    public static boolean validarInyecciónSQL(String parametro){
        boolean respuesta = true;
         for (int i = 0; i < parametro.length(); i++) {
            if (parametro.charAt(i) == '=') {
                respuesta = false;
                break;
            }
        }
        return respuesta;
    }
}
