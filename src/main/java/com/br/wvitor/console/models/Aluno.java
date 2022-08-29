package com.br.wvitor.console.models;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.br.wvitor.console.interfaces.IEntidade;
import com.br.wvitor.console.interfaces.IPersistencia;
import com.br.wvitor.console.service.PersistenciaJson;

public class Aluno implements IEntidade {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private List<Float> notas;

    public List<Float> getNotas() {
        return notas;
    }

    public void setNotas(List<Float> notas) {
        this.notas = notas;
    }

    public String situacao() {
        var media = this.media();
        if (media < 5)
            return "Reprovado";
        else if (media >= 5 && media < 7)
            return "Recuperação";
        else
            return "Aprovado";
    }

    public float media() {
        float mediaCalculada = (float) 0.0;

        if (this.notas != null) {
            for (Float nota : this.notas) {
                mediaCalculada += nota;
            }

            mediaCalculada = mediaCalculada / this.notas.size();
        }

        return mediaCalculada;
    }

    public void salvar(IPersistencia persistencia) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        persistencia.salvar(this);
    }

    public static List<IEntidade> all() {
        return new PersistenciaJson(new Aluno().getClass()).Todos();
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
