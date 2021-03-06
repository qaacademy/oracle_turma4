import org.junit.*;

import java.sql.*;

public class TestConexaoBdOracle {
    static Connection connection;
    static Statement stmt;

    @BeforeClass
    public static void before() throws SQLException {
        System.out.println("Iniciando teste");
        String serverName = "ec2-18-204-44-237.compute-1.amazonaws.com";
        String portNumber = "1521";
        String sid = "XE";
        String user = "system";
        String password = "qaacademy";
        String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
        connection = DriverManager.getConnection(url, user, password);//Conexão de rede
        System.out.println("Conexão realizada com sucesso!");
        stmt = connection.createStatement();
    }


    @Test
    public void testeConexaoBancoDeDados() throws SQLException {

        String comando_sql = "SELECT MASSA_TESTE  FROM dados_teste dt WHERE dt.ID_CASO_TESTE = 1";
        ResultSet resultSet = stmt.executeQuery(comando_sql);
        resultSet.next();//Pula para o primeiro registro
        String jsonMassaTeste = resultSet.getString(1);
        Assert.assertTrue("Massa de teste inexistente", jsonMassaTeste.contains("https://amazon.com.br"));
    }
    @AfterClass
    public static void after() throws SQLException {
        stmt.close();
        connection.close();
        System.out.println("Conexão fechadas com sucesso!");
    }
}
