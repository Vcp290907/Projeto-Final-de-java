import controllers.LoginController;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando aplicação");
        LoginController controller = new LoginController();
        controller.iniciar();
    }
}