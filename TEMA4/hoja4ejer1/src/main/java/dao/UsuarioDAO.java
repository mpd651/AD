/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author usuario
 */
public interface UsuarioDAO extends DAOGenerico<Usuario, String>
{
    public List<Usuario> getUsuariosMayoresEdad();
}
