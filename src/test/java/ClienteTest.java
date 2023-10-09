import org.example.Administrador;
import org.example.BD;
import org.example.Cliente;
import org.example.ClienteProxy;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ClienteTest {
    @BeforeEach
    void setUp() {
        BD.addCliente(new Cliente(1, "Antônio", "antonio.silvapr@gmail.com","senha321"));
        BD.addCliente(new Cliente(2, "Rosa", "rosa.maria21@hotmail.com","133645"));
    }

    @Test
    public void deveRetornarDadosPessoaisCliente() {
        ClienteProxy cliente = new ClienteProxy(1);

        assertEquals(Arrays.asList("Antônio", "antonio.silvapr@gmail.com"), cliente.obterDadosPessoais());
    }

    @Test
    public void deveRetonarSenhaCliente() {
        Administrador administrador = new Administrador("Carol", true);
        ClienteProxy cliente = new ClienteProxy(2);

        assertEquals("133645", cliente.obterSenha(administrador));
    }

    @Test
    public void deveRetonarExcecaoUsuarioNaoAutorizadoConsultarNotaSenha() {
        try {
            Administrador administrador = new Administrador("Marcos", false);
            ClienteProxy cliente = new ClienteProxy(2);

            cliente.obterSenha(administrador);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Funcionário não autorizado", e.getMessage());
        }
    }
}
