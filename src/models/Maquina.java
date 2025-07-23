package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Maquina implements Comparable<Maquina> {
    private String nombre;
    private String ip;
    private List<Integer> codigos;
    
    //variables calculables
    private int subRed;
    private int riesgo;
    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;

        this.subRed = calcularSubred();
        this.riesgo = calcularRiesgo();
    }

    

    private int calcularSubred() {
        String[] octetos = ip.split("\\.");
        if (octetos.length == 4) {
            try {
                return Integer.parseInt(octetos[3]); 
            } catch (NumberFormatException e) {
                return 0; 
            }
        }
        return 0; 
    }

    private int calcularRiesgo() {
        int sumaCodigosDivisiblesPor3 = 0;
        if (codigos != null) {
            for (int codigo : codigos) {
                if (codigo % 3 == 0) {
                    sumaCodigosDivisiblesPor3 += codigo;
                }
            }
        }

        String nombreSinEspacios = nombre.replace(" ", "");
        Set<Character> caracteresUnicos = new HashSet<>();
        for (char c : nombreSinEspacios.toCharArray()) {
            caracteresUnicos.add(c);
        }
        int numeroCaracteresUnicos = caracteresUnicos.size();

        return sumaCodigosDivisiblesPor3 * numeroCaracteresUnicos;
    }

    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((codigos == null) ? 0 : codigos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj==null || getClass() != obj.getClass())
            return false;
        Maquina maquina = (Maquina)obj;
        return subRed == maquina.calcularSubred() && obj.equals(maquina.nombre);
    }

    

    @Override
    public int compareTo(Maquina otrMaquina) {
        int comparacionSubred = Integer.compare(this.subRed, otrMaquina.subRed);
        if (comparacionSubred!=0) {
            return comparacionSubred;
        }
        return this.nombre.compareTo(otrMaquina.nombre);
    }

    @Override
    public String toString() {
        return "Maquina [nombre=" + nombre + ", ip=" + ip + ", codigos=" + codigos + ", subRed=" + subRed + ", riesgo="
                + riesgo + "]";
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getIp() {
        return ip;
    }



    public void setIp(String ip) {
        this.ip = ip;
    }



    public List<Integer> getCodigos() {
        return codigos;
    }



    public void setCodigos(List<Integer> codigos) {
        this.codigos = codigos;
    }



    public int getSubRed() {
        return subRed;
    }



    public void setSubRed(int subRed) {
        this.subRed = subRed;
    }



    public int getRiesgo() {
        return riesgo;
    }



    public void setRiesgo(int riesgo) {
        this.riesgo = riesgo;
    }
    
}
