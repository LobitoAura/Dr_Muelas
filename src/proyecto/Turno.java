package proyecto;

public class Turno {
	private int idTurno; 
	private int dniUsers; 
	private String Fecha; 
	private String Hora; 
	private int NroCons;
	private boolean Estado;
	
	public Turno(int idTurno, int dniUsers, String fecha, String hora, int nroCons, boolean estado) {
		this.idTurno = idTurno;
		this.dniUsers = dniUsers;
		this.Fecha = fecha;
		this.Hora = hora;
		this.NroCons = nroCons;
		this.Estado = estado;
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public int getDniUsers() {
		return dniUsers;
	}

	public void setDniUsers(int dniUsers) {
		this.dniUsers = dniUsers;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public int getNroCons() {
		return NroCons;
	}

	public void setNroCons(int nroCons) {
		NroCons = nroCons;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}
	
	
}