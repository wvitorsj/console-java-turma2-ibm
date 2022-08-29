package com.br.wvitor.console.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.br.wvitor.console.interfaces.IEntidade;
import com.br.wvitor.console.interfaces.IPersistencia;

public class PersistenciaCSV implements IPersistencia {
    private Class<?> tipo;

    public PersistenciaCSV(Class<?> tipo) {
        this.tipo = tipo;
    }

    public void salvar(IEntidade entidade) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        List<IEntidade> entidades = this.Todos();
        entidades.add(entidade);
        try {
            FileWriter myWriter = new FileWriter(tipo.getName() + ".csv");
            String entidadesCSV = "";
            for (IEntidade iEntidade : entidades) {
                entidadesCSV += iEntidade.getNome() + ";\n";
            }
            myWriter.write(entidadesCSV);
            myWriter.close();
        } catch (IOException e) {
        }
    }

    public List<IEntidade> Todos() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        List<IEntidade> entidades = new ArrayList<IEntidade>();
        try {
            File myObj = new File(tipo.getName() + ".csv");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                var construct = this.tipo.getConstructor();
                var object = construct.newInstance();
                final IEntidade entidade = (IEntidade) object;
                entidade.setNome(myReader.nextLine());
                entidades.add(entidade);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
        }

        return entidades != null ? entidades : new ArrayList<IEntidade>();
    }

}
