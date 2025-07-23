package controllers;

import models.Maquina;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue; 
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.LinkedList; 

public class MaquinaController {

    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> pilaFiltrada = new Stack<>();
        for (Maquina maquina : maquinas) {
            if (maquina.getSubRed() < umbral) {
                pilaFiltrada.push(maquina); 
            }
        }
        return pilaFiltrada;
    }

    public TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        TreeSet<Maquina> maquinasOrdenadas = new TreeSet<>();
        maquinasOrdenadas.addAll(pila);
        return maquinasOrdenadas;
    }


   public TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {

        TreeMap<Integer, Queue<Maquina>> maquinasAgrupadas = new TreeMap<>();

        for (Maquina maquina : maquinas) {
            int riesgo = maquina.getRiesgo();

            maquinasAgrupadas.computeIfAbsent(riesgo, k -> new LinkedList<>()).add(maquina);
        }
        return maquinasAgrupadas;
    }

    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        Stack<Maquina> grupoExplotado = new Stack<>();
        int mayorCantidad = -1;
        Integer mayorRiesgoConMayorCantidad = null;

        if (mapa == null || mapa.isEmpty()) {
            return grupoExplotado; 
        }

           for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
            Integer riesgoActual = entry.getKey();
            Queue<Maquina> colaActual = entry.getValue();
            int cantidadActual = colaActual.size();

            if (cantidadActual > mayorCantidad) {
                mayorCantidad = cantidadActual;
                mayorRiesgoConMayorCantidad = riesgoActual;
            } else if (cantidadActual == mayorCantidad) {
                if (mayorRiesgoConMayorCantidad == null || riesgoActual > mayorRiesgoConMayorCantidad) {
                    mayorRiesgoConMayorCantidad = riesgoActual;
                }
            }
        }

 
        if (mayorRiesgoConMayorCantidad != null) {
            Queue<Maquina> colaMayorGrupo = mapa.get(mayorRiesgoConMayorCantidad);
                 Stack<Maquina> tempStack = new Stack<>();
            for (Maquina maquina : colaMayorGrupo) {
                tempStack.push(maquina);
            }
             while(!tempStack.isEmpty()){
                grupoExplotado.push(tempStack.pop());
            }

        }

        return grupoExplotado;
    }
}