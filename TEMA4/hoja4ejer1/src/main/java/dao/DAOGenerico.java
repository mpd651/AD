/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author usuario
 */
public interface DAOGenerico <E, K>
{
    public boolean insertar(E entity);
    public void actualizar (E entity);
    public void borrar (E entity);
    public E buscar (K key);
    public List<E> getTodos();
    
}
