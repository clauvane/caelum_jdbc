package br.caelum.clauvane.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.caelum.clauvane.modelo.Contato;

public class ContatoDao {
	private Connection connection = null;
	private PreparedStatement ps = null;
	
	public boolean addContato(Contato contato){
		String sql = "insert into contato (nome,endereco,email,dataNascimento) values (?,?,?,?)";
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEndereco());
			ps.setString(3, contato.getEmail());
			Date date = new Date(contato.getDataNascimento().getTimeInMillis());
			ps.setDate(4, date);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				connection.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean delContato(long id){
		String sql = "delete from contato where id = ?";
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				connection.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean alterContato(Contato contato){
		String sql = "update contato set nome = ?,endereco = ?,email = ?,dataNascimento = ? where id = ?";
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEndereco());
			ps.setString(3, contato.getEmail());
			Date date = new Date(contato.getDataNascimento().getTimeInMillis());
			ps.setDate(4, date);
			ps.setLong(5, contato.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				connection.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public List<Contato> getContatos(){
		List<Contato> contatos = new ArrayList<Contato>();
		String sql = "select * from contato";
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEndereco(rs.getString("endereco"));
				contato.setEmail(rs.getString("email"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				contatos.add(contato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				connection.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}
	
	public Contato getContato(long id){
		Contato contato = null;
		String sql = "select * from contato where id = ?";
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEndereco(rs.getString("endereco"));
				contato.setEmail(rs.getString("email"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				connection.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contato;
	}
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}
}
