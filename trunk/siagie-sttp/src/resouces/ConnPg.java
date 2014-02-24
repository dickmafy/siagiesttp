package resouces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnPg {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String CONNECTION_STRING = "jdbc:postgresql://190.12.76.5:5432/sttp_prod";
	private static final String USER = "postgres";
	private static final String PASSWORD = "aprolab2013";
	private static Connection CONEXION;
	private static boolean IS_INSTANCE;

	static {
		CONEXION = null;
		IS_INSTANCE = false;
	}

	public static boolean conectar() throws ClassNotFoundException,
			SQLException {
		Class.forName(DRIVER);
		CONEXION = DriverManager.getConnection(CONNECTION_STRING, USER,
				PASSWORD);
		if (CONEXION != null) {
			return true;
		} else {
			return false;
		}
	}

	public static Connection getConexion() throws ClassNotFoundException,
			SQLException {
		if (CONEXION == null) {
			IS_INSTANCE = conectar();
		}
		return CONEXION;
	}

	public static void cerrarConexion() throws SQLException {
		CONEXION.close();
	}

	public static boolean isInstance() {
		return IS_INSTANCE;
	}

	public static void setInstance(boolean instance) {
		IS_INSTANCE = instance;
	}
}
