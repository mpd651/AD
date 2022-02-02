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
    
    
    public String obtenerProfesorDeCurso (String idCurso){
        String nombreProfe="";
        String sql="select profesor.nombre from profesor inner join curso where curso.id=?";
        try ( PreparedStatement consultaPreparada = con.prepareStatement(sql)){
            consultaPreparada.setString(1, idCurso);
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
                curso.setId(result.getString("id"));
                curso.setNombre(result.getString("nombre"));
                curso.setTutor(null);
                curso.setAlumnos(obtenerAlumnosCurso(result.getString("id")));
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
                String id=result.getString("id");
                curso.setId(id);
                curso.setNombre(result.getString("nombre"));
                curso.setTutor(obtenerProfesor(result.getInt("tutor_id")));
                curso.setAlumnos(obtenerAlumnosCurso(id));
                cursos.add(curso);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }
    
    public List<Curso> obtenerCursosSinAlumnos()
    {
        List<Curso> cursos = new ArrayList();
        try
        {
            String sql = "select * from curso c where c.id not in (SELECT c2.id from curso c2 inner join alumno a on c2.id=a.curso)";
            Statement consulta = con.createStatement();
            ResultSet result = consulta.executeQuery(sql);
            while (result.next()){
                Curso curso=new Curso();
                String id=result.getString("id");
                curso.setId(id);
                curso.setNombre(result.getString("nombre"));
                //curso.setTutor(obtenerProfesor(result.getInt("tutor_id")));
                //curso.setAlumnos(obtenerAlumnosCurso(id));
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
            ResultSet result2 = consulta.executeQuery(sql);
            while (result2.next()){
                Curso curso=new Curso();
                curso.setAlumnos(obtenerAlumnosCurso(result2.getString("id")));
                curso.setId(result2.getString("id"));
                curso.setNombre(result2.getString("nombre"));
                curso.setTutor(obtenerProfesor(result2.getInt("tutor_id")));
                cursos.add(curso);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }
    
    
    
    public List<Alumno> obtenerAlumnosCurso(String cursoId){
        List<Alumno> alumnos=new ArrayList();
        
        try{
            String sql="select * from alumno where curso=?";
            PreparedStatement consultaPreparada = con.prepareStatement(sql);
            consultaPreparada.setString(1, cursoId);
            ResultSet result=consultaPreparada.executeQuery();
            
            while (result.next()){
                Alumno alumno=new Alumno();
                alumno.setId(result.getInt("id"));
                alumno.setNombre(result.getString("nombre"));
                alumno.setNota_media(result.getFloat("nota_media"));
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
        List<Profesor> profesores= new ArrayList();
        try
        {
            String sql="select * from profesor p where p.id not in (select p2.id from profesor p2 inner join curso c on p2.id=c.tutor_id)";
            Statement consulta=con.createStatement();
            ResultSet result=consulta.executeQuery(sql);
            
            while (result.next()){
                Profesor p=new Profesor();
                p.setFecha_nacimiento(result.getDate("fecha_nacimiento").toLocalDate());
                p.setId(result.getInt("id"));
                p.setNombre(result.getString("nombre"));
                p.setCursos(obtenerCursosconProfesor(result.getInt("id")));
                profesores.add(p);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profesores;
    }
    
    public Curso obtenerCursoDeAlumno(int idAlumno)
    {
        Curso curso=new Curso();
        try
        {
            String sql = "select * from curso where id = (select curso from alumno where id=?)";
            PreparedStatement consultaPreparada = con.prepareStatement(sql);
            consultaPreparada.setInt(1, idAlumno);
            ResultSet result = consultaPreparada.executeQuery(sql);
            while (result.next()){
                curso.setAlumnos(obtenerAlumnosCurso(result.getString("id")));
                curso.setId(result.getString("id"));
                curso.setNombre(result.getString("nombre"));
                curso.setTutor(obtenerProfesor(result.getInt("tutor_id")));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curso;
    }
    
    public Curso obtenerCursoConMasAlumnos(){
        Curso curso=new Curso();
        try
        {
            String sql="select nombre from curso c where c.id = (select curso from alumno a group by curso order by count(id) desc limit 1)";
            Statement consulta=con.createStatement();
            ResultSet result=consulta.executeQuery(sql);
            
            while (result.next()){
                curso.setNombre(result.getString("nombre"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curso;
        
    }
    
    public List<Alumno> obtenerAlumnosConMasNota(int numAlumnosMax){
        List<Alumno> alumnos=new ArrayList();
        String sql= "select * from alumno a order by nota_media desc limit ?";
        PreparedStatement consultaPreparada;
        try
        {
            consultaPreparada = con.prepareStatement(sql);
            consultaPreparada.setInt(1, numAlumnosMax);
            ResultSet result=consultaPreparada.executeQuery();
            
            while (result.next()){
                Alumno a=new Alumno();
                a.setId(result.getInt("id"));
                a.setNombre(result.getString("nombre"));
                a.setNota_media(result.getFloat("nota_media"));
                a.setCurso(obtenerCursoDeAlumno(result.getInt("id")));
                alumnos.add(a);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(GestorBd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }

    
    
}
