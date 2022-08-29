package com.br.wvitor.console.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IPersistencia {
  public void salvar(IEntidade entidade) throws InstantiationException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;

  public List<IEntidade> Todos() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException;
}
