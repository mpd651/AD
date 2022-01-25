package es.marioperez.ejercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GestorBd {
    public Connection con;

    public GestorBd()
    {
        con=Conexion.getInstance().getConnection();
    }
    
    
    public String obtenerProfesorDeCurso (int idCurso){
        String nombreProfe="";
        String sql="select profesor.nombre from profesor inner join curso where curso.id=?";
        try ( PreparedStatement consultaPreparada = con.prepareStatement(sql)){
            consultaPreparada.setInt(1, idCurso);
            ResultSet result=consultaPreparada.executeQuery();
            while (result.next()){
                nombreProfe=result.getString("nombre");
            }
        } catch (SQLException ex)
        {
            System.err.println("Error al insertar un grupo");
        }
        return nombreProfe;
    }
    
    public List<Curso> obtenerCursosSinTutor(){
        List<Curso> cursos=new ArrayList();
        try
        {
            String sql="select * from curso where tutor_id is null";
            Statement consulta=con.createStatement();
            ResultSet result=consulta.executeQuery(sql);
            
            while (result.next()){
                Curso curso=new Curso();
                curso.setId(result.getInt("id"));
                curso.setNombre(result.getString("nombre"));
                curso.setTutor(null);
                curso.setAlumnos(obtenerAlumnosCurso(result.getInt("id")));
                cursos.add(curso);
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }
    
    public List<Curso> obtenerCursos()
    {
        List<Curso> cursos = new ArrayList();
        try
        {
            String sql = "select * from curso";
            Statement consulta = con.createStatement();
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()){
                Curso curso=new Curso();
                curso.setAlumnos(obtenerAlumnosCurso(result.getInt("id")));
                curso.setId(result.getInt("id"));
                curso.setNombre(result.getString("nombre"));
                curso.setTutor(obtenerProfesor(result.getInt("tutor_id")));
                cursos.add(curso);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }
    
        public List<Curso> obtenerCursosconProfesor(int idProfesor)
    {
        List<Curso> cursos = new ArrayList();
        try
        {
            String sql = "select * from curso where tutor_id='"+idProfesor+"'";
            Statement consulta = con.createStatement();
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()){
                Curso curso=new Curso();
                curso.setAlumnos(obtenerAlumnosCurso(result.getInt("id")));
                curso.setId(result.getInt("id"));
                curso.setNombre(result.getString("nombre"));
                curso.setTutor(obtenerProfesor(result.getInt("tutor_id")));
                cursos.add(curso);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }
    
    
    
    public List<Alumno> obtenerAlumnosCurso(int cursoId){
        List<Alumno> alumnos=new ArrayList();
        
        try{
            String sql="select * from alumno where curso=?";
            PreparedStatement consultaPreparada = con.prepareStatement(sql);
            consultaPreparada.setInt(1, cursoId);
            ResultSet result=consultaPreparada.executeQuery();
            
            while (result.next()){
                Alumno alumno=new Alumno();
                alumno.setId(result.getInt("id"));
                alumno.setNombre(result.getString("nombre"));
                alumno.setNota_media(result.getString("nota_media"));
                alumnos.add(alumno);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return alumnos;
    }
    
    public Profesor obtenerProfesor(int idProfesor){
        Profesor profesor=new Profesor();
        
        try{
            String sql="select * from profesor where id=?";
            PreparedStatement conPreparada=con.prepareStatement(sql);
            conPreparada.setInt(1, idProfesor);
            ResultSet result=conPreparada.executeQuery();
            
            while (result.next()){
                profesor.setId(idProfesor);
                profesor.setFecha_nacimiento(result.getDate("fecha_nacimiento").toLocalDate());
                profesor.setNombre(result.getString("nombre"));
                profesor.setCursos(obtenerCursosconProfesor(idProfesor));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return profesor;
    }
    
    public List<Profesor> obtenerProfesoresNoTutores(){
        String sql="select * from profesor inner join curso not in ";
        
        
    }
    
}
