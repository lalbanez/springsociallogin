
public class Mock {
public static void main(String[] args) {
	String texto = "Olá, %s";
	texto = String.format(texto, "Leandro");
	System.out.println(texto);
}
}
